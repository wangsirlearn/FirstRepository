package org.example.Utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component

public class OSSUtil {
        //参数配置化 将一些可以灵活配置的参数写在yml文件中 使用@Vuelue注解注入值
//        private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//        private String bucketName = "java-ai-study-wys";
//        private String region = "cn-beijing";
    //参数太多 一个个注入太麻烦了  可以创建一个实体类 封装参数 使用@ConfigurationProperties
//        @Value("${aliyun.myoss.endpoint}")
//        private String endpoint;
//        @Value("${aliyun.myoss.bucketName}")
//        private String bucketName;
//        @Value("${aliyun.myoss.region}")
//        private String region;
        @Autowired
        private OSSUtils ossUtils;

        public String upload(byte[] content, String originalFilename) throws Exception {
            String endpoint = ossUtils.getEndpoint();
            String bucketName = ossUtils.getBucketName();
            String region = ossUtils.getRegion();
            // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
            EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

            // 填写Object完整路径，例如202406/1.png。Object完整路径中不能包含Bucket名称。
            //获取当前系统日期的字符串,格式为 yyyy/MM
            String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
            //生成一个新的不重复的文件名
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = dir + "/" + newFileName;

            // 创建OSSClient实例。
            ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
            clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
            OSS ossClient = OSSClientBuilder.create()
                    .endpoint(endpoint)
                    .credentialsProvider(credentialsProvider)
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(region)
                    .build();

            try {
                ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            } finally {
                ossClient.shutdown();
            }

            return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
        }

    }
