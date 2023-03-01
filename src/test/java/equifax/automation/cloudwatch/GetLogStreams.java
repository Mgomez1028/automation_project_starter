package equifax.automation.cloudwatch;

import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.DescribeLogStreamsResponse;
import software.amazon.awssdk.services.cloudwatchlogs.model.OrderBy;

public class GetLogStreams {
    public static String getLastLogStreamFromLogGroup(CloudWatchLogsClient cloudWatchLogsClient, String logGroupName) {
        String nextToken = null;
        DescribeLogStreamsResponse result = cloudWatchLogsClient
                .describeLogStreams(DescribeLogStreamsRequest.builder()
                        .logGroupName(logGroupName)//this value is received by parameter
                        .orderBy(OrderBy.LAST_EVENT_TIME)
                        .descending(true)
                        .nextToken(nextToken)
                        .build());

        return result.logStreams().stream().findFirst().get().logStreamName();
    }
}
