package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.LogDTO;
import com.example.accessingdatamysql.repository.LogRepository;
import com.example.accessingdatamysql.transformers.LogTransformer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.directory.InvalidAttributesException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class LogServiceImplTest {
    private static final String NULL_MESSAGE_EXCEPTION = "Logger message is null.";

    private static final String EMPTY_MESSAGE_EXCEPTION = "Logger message is empty.";

    private static final String LATE_DATE_EXCEPTION = "Data is after 24/03/2025";

    @Mock
    private LogRepository logRepository;

    @Mock
    private LogTransformer logTransformer;

    @InjectMocks
    LogServiceImpl logServiceImplUnderTest = new LogServiceImpl();

    private static final IllegalArgumentException logNullMessageException =
            new IllegalArgumentException(NULL_MESSAGE_EXCEPTION);

    private static final InvalidAttributesException logEmptyMessageException =
            new InvalidAttributesException(EMPTY_MESSAGE_EXCEPTION);

    private static final DateTimeException lateDateException =
            new DateTimeException(LATE_DATE_EXCEPTION);

    private static final LogDTO logEmptyMessageDTO = LogDTO.builder()
            .message("")
            .build();

    private static final LogDTO logNullMessageDTO = LogDTO.builder()
            .message(null)
            .build();

    private static final LogDTO logLateDateGoodMessageDTO = LogDTO.builder()
            .message("Good message")
            .date(new Date(2025, 4, 1))
            .build();

    private static final LogDTO logLateDateNullMessageDTO = LogDTO.builder()
            .message(null)
            .date(new Date(2025, 4, 1))
            .build();

    private static Stream<Arguments>  provideLogsToThrowException() {
        return Stream.of(
                Arguments.of(logEmptyMessageDTO, logEmptyMessageException),
                Arguments.of(logNullMessageDTO, logNullMessageException),
                Arguments.of(logLateDateGoodMessageDTO, lateDateException),
                Arguments.of(logLateDateNullMessageDTO, logNullMessageException)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLogsToThrowException")
    public void testSaveLogThrowsException(LogDTO faultyLogDTO, Exception exceptionNeeded){
        Exception exception = assertThrows(exceptionNeeded.getClass(), () -> {
            logServiceImplUnderTest.saveLog(faultyLogDTO);
        });
        assertEquals(exceptionNeeded.getMessage(), exception.getMessage());
        verifyNoInteractions(logTransformer);
        verifyNoInteractions(logRepository);
    }
}
