# Library Management

## 项目需求
通过Java EE，在线下环境开发设计一个图书馆管理系统，在这个系统中可以实现图书馆中图书信息管理及图书馆工作人员管理等功能。

## MySQL表结构
book_category&nbsp;&nbsp;&nbsp;---------- 书籍种类</br>
book_detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---------- 具体书籍</br>
user_detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---------- 用户详情</br>
user_security&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---------- 用户安全信息（包括密码等）</br>
library&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---------- 图书馆</br>
log&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---------- 日志</br>

## 项目分包
cn.albumen.library.annotation </br>
cn.albumen.library.bean </br>
cn.albumen.library.config </br>
cn.albumen.library.constant </br>
cn.albumen.library.controller </br>
cn.albumen.library.dao </br>
cn.albumen.library.dto </br>
cn.albumen.library.security </br>
cn.albumen.library.service </br>
cn.albumen.library.util </br>

## API文档
[eoLinker](https://www.eolinker.com/#/share/index?shareCode=DnawFZ)

## 环境配置
1. ~~修改 cn.albumen.library.constant.SecurityConfig 中 PEM_KEY_PATH 所指向的地址到当前项目路径下Key目录~~
2. 运行 ./sql/library.sql 导入数据库

## RequestWrapper
### 原因
1. HttpServletRequest的getInputStream使用后不能像其他Stream一样回到头部以后交给下一级还可以正常使用
2. 本项目中使用了JSON替代了传统的表单提交，而我们在验证用户登录时需要获取一次Body中的loginUserId作为验证
### 解决方案
重写 Servlet 默认的 HttpServletRequestWrapper 使 InputStream 可重复使用

## JWT
在 /Key/pem 中存放了RSA私钥，通过RSAKeyUtil读取私钥并生成公钥供JWT使用。
```Java
cn.albumen.library.util.RsaKeyUtil
BufferedReader br = new BufferedReader(new FileReader(PEM_KEY_PATH));
Security.addProvider(new BouncyCastleProvider());
KeyPair kp = (KeyPair) new PEMReader(br).readObject();
this.privateKey = (RSAPrivateKey) kp.getPrivate();
this.publicKey = (RSAPublicKey) kp.getPublic();
```
由于私钥转换使用了较长时间为了以及提高密钥的复用性，REAKeyUtil使用了工厂模式，仅在第一次运行时进行私钥的转换，第二次开始则从内存中直接读取数据。
```Java
cn.albumen.library.util.JwtUtil
private static RSAPublicKey publicKey = RsaKeyUtil.getInstance().getPublicKey();
private static RSAPrivateKey privateKey = RsaKeyUtil.getInstance().getPrivateKey();
```
JwtUtil使用 com.auth0.jwt 对JWT的支持，直接生成token。</br>
注：此处签发token的有效期由 cn.albumen.library.constant.SecurityConfig 中的EXP_TIME决定，单位为毫秒。
```Java
token = JWT.create()
            .withIssuer("Albumen")
            .withSubject(userName)
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXP_TIME))
            .withArrayClaim("Permission", permission)
            .sign(algorithmRS);
```
验证token的核心代码
```Java
JWTVerifier verifier = 
            JWT.require(algorithmRS)
               .withIssuer("Albumen")
               .withSubject(userName)
               .build();
DecodedJWT jwt = verifier.verify(token);
```

## SpringSecurity + JWT
### 配置时会出现的问题
1. 全局进行认证会导致永远不能认证成功，登录时陷入认证的死循环，抛出NPE
2. 采用JSON提交登录页与默认表单提交不兼容，无法获取到用户名和密码
3. 登陆页面加入白名单后SpringSecurity就不再拦截了
4. 登陆成功以后默认返回成功信息不是原生支持JWT
5. 不采用默认认证方式，RBAC获取不到之前登陆时候的角色
6. 自定义认证时从Body中直接获取loginUserId会导致后续Controller无法获取到Body的内容
### 解决方案
1. 将登陆api加入到permit列表中
2. 重写默认用户认证拦截器（UsernamePasswordAuthenticationFilter）
   ```Java
   http.addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
   ```
3. 在重写的认证拦截器中加入对登录页的拦截
   ```Java
   filter.setFilterProcessesUrl("/usersecurity/login");
   ```
4. 重写身份验证组件（AuthenticationProvider）
   ```Java
   auth.authenticationProvider(customAuthenticationProvider);
   ```
5. 重写Token验证组件（BasicAuthenticationFilter），在成功验证后读取JWT中的角色并返回到 UsernamePasswordAuthenticationToken 中
   ```Java
   http.addFilter(new CustomAuthenticationFilter(authenticationManager(), redisUtil))

   ...

   //验证成功
    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
    for (String permission : ret) {
        authorities.add(new GrantedAuthorityImpl(permission));
    }
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
   ```
6. 通过前文重写的 RequestWrapper 解决
### Token的保存
虽然通过JWT的加密，从客户端返回的Token一定是正确的，但为了管理用户的在线状态，我还是把Token存入 Redis 借助 Redis 的 TTL 来验证用户状态。做到在服务端就可以使用户下线。