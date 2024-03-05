FROM openjdk
ADD target/unit-test-mockmvc.jar unit-test-mockmvc.jar
ENV profile=test
ENTRYPOINT ["java","-jar","/unit-test-mockmvc.jar"]
