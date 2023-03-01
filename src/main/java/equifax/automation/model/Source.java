package equifax.automation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Source {
    private String source;
    private String component;
    private String subcomponent;
    private String phase;
    private String activitytype;
    private String status;
    private String mode;
    private String eventname;
    private double eventcount;
    private List<String> dfcorrelationids;
    private String firsttimestamp;
    private String latesttimestamp;
    private List<String> filenames;
    private List<String> dfsourceids;
}
