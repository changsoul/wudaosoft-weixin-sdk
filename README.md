# wudaosoft-weixin-sdk
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.wudaosoft/wudaosoft-weixin-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.wudaosoft/wudaosoft-weixin-sdk/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Wudaosoft Weixin Official SDK.

## Download

- http://repo1.maven.org/maven2/com/wudaosoft/wudaosoft-weixin-sdk/  
- [the latest JAR][1]  
[1]: https://search.maven.org/remote_content?g=com.wudaosoft&a=wudaosoft-weixin-sdk&v=LATEST

## Maven

```xml
<dependency>
    <groupId>com.wudaosoft</groupId>
    <artifactId>wudaosoft-weixin-sdk</artifactId>
    <version>2.0.4</version>
</dependency>
```
## Gradle/Grails

``` groovy
compile 'com.wudaosoft:wudaosoft-weixin-sdk:2.0.4'
```

## Getting started

You need to add the `@EnableWeiXinOfficial` annotation to one of your `@Configuration` classes, the Weixin Official API be enabled.
```java
package com.example.myproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.wudaosoft.weixinsdk.annotation.EnableWeiXinOfficial;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;

@Configuration
@EnableWeiXinOfficial
public class Application {

	@Bean
	public WeiXinConfig weiXinConfig() {
		return new WeiXinConfig("appid", "appsecret");
	}
	
	// ...

}
```

Now, you can use the Weixin Official APIs.

For example, to get OAuth2 Userinfo data would be:
```java
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wudaosoft.weixinsdk.oauth2.OAuth2AccessToken;
import com.wudaosoft.weixinsdk.oauth2.OAuth2Api;
import com.wudaosoft.weixinsdk.oauth2.OAuth2UserInfo;

@Service
public class WeixinService {

	@Autowired
	private OAuth2Api oauth2Api;

	public void doAuth(String code, String state) {
		OAuth2AccessToken authToken = oauth2Api.getOauth2AccessToken(code);
    	
    		if (authToken.getErrcode() != 0) {
    			// throw an exception
    		}
    	
    		OAuth2UserInfo userInfo = oauth2Api.oauth2UserInfo(authToken.getAccess_token(), authToken.getOpenid(), "zh_CN");
    	
    		if (userInfo.getErrcode() != 0) {
    			// throw an exception
    		}
    	
    		System.out.println(String.format("nickname: %s", userInfo.getNickname()));
    	
    		// do something you want ...
	}

	// ...

}
```

Here are APIs list:
- ArticlesApi
- CustomMsgSender
- MaterialApi
- MenuApi
- OAuth2Api
- QRCodeApi
- UserApi
- UserGroupApi
- CommonApi for static way

### *License*

wudaosoft-weixin-sdk is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2009-2017 Wudao Software Studio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
