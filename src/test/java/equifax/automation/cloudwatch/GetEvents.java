package equifax.automation.cloudwatch;

import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.FilterLogEventsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.FilteredLogEvent;

import java.util.List;

import static equifax.automation.enums.CloudWatchConstants.FILTER_PATTERN_REQUEST_ID;


public class GetEvents {
    public static List<FilteredLogEvent> filterCWLogEvents(CloudWatchLogsClient cloudWatchLogsClient, String logGroupName, String logStreamName) {
        System.out.println("filtrando correlationId ");
        FilterLogEventsRequest filterLogEventsRequest = FilterLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamNames(logStreamName)
                .filterPattern(String.valueOf(FILTER_PATTERN_REQUEST_ID))
                .build();


        int logLimit = cloudWatchLogsClient.filterLogEvents(filterLogEventsRequest).events().size();
        System.out.println(logLimit);

        for (int i = 0; i < logLimit; i++) {
            System.out.println(cloudWatchLogsClient.filterLogEvents(filterLogEventsRequest).events().get(i).message());
        }
        return cloudWatchLogsClient.filterLogEvents(filterLogEventsRequest).events();
    }

    public static String filterCWLogEvent(CloudWatchLogsClient cloudWatchLogsClient, String logGroupName, String logStreamName, Long milliSeconds)  {
        System.out.println("Filtrando correlationId in log events........");
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        FilterLogEventsRequest filterLogEventsRequest = FilterLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamNames(logStreamName)
                .filterPattern(String.valueOf(FILTER_PATTERN_REQUEST_ID))
                .startTime(milliSeconds)
                .build();

        int logLimit = cloudWatchLogsClient.filterLogEvents(filterLogEventsRequest).events().size();//todo EXPLICIT WAIT IF LISTA ES 0 LLAME EL FILTRO NUEVAMENTE
        System.out.println("Loaded events " + logLimit);

        return cloudWatchLogsClient.filterLogEvents(filterLogEventsRequest).events().stream().findFirst().get().message();
    }
}

