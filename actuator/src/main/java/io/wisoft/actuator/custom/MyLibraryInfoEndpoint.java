package io.wisoft.actuator.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Slf4j
//@Endpoint(id = "myLibraryInfo")
@WebEndpoint(id = "myLibraryInfo")
// jmx를 허용하지 않는 web용 엔드포인트 - @RestControllerEndpoint, @ServletEndpoint는 사용 지양(프로메테우스와 같은 라이브러리와 호환성 문제 발생)
public class MyLibraryInfoEndpoint {

    /**
     * RequestBody 방식으로 파라미터 수신
     */
    @WriteOperation
    public void changeSomething(final String name, final boolean enableSomething) { // Actuator에서 복합 객체(ex: Dto)는 바디로 받을 수 없음
        log.info("name: {}, enableSomething: {}", name, enableSomething);
    }

    /**
     * PathVariable 방식으로 파라미터 수신
     */
    @ReadOperation
    public String getPathTest(@Selector(match = Selector.Match.ALL_REMAINING) String[] path) { // 여러 개의 Path 수신 가능
        log.info("path: {}", Arrays.asList(path));
        return "path:" + Arrays.asList(path);
    }

    /**
     * 쿼리 스트링 방식으로 파라미터 수신
     */
    @ReadOperation
    public List<LibraryInfo> getLibraryInfos(final @Nullable String name, final boolean includeVersion) {

        final LibraryInfo libraryInfo = new LibraryInfo();
        libraryInfo.setName("logback");
        libraryInfo.setVersion("1.0.0");

        final LibraryInfo libraryInfo2 = new LibraryInfo();
        libraryInfo2.setName("jackson");
        libraryInfo2.setVersion("2.0.0");

        List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);

        if (name != null) {
            libraryInfos = libraryInfos.stream()
                    .filter(lib -> lib.getName().equals(name))
                    .toList();
        }

        if (!includeVersion) {
            libraryInfos = libraryInfos.stream()
                    .map(lib -> {
                        final LibraryInfo temp = new LibraryInfo();
                        temp.setName(lib.getName());
//                        temp.setVersion(lib.getVersion());
                        return temp;
                    })
                    .toList();
        }

        return libraryInfos;
    }
}
