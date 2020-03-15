package com.lenovo.manufacture.Item4;

public class StepMsg {

    /**
     * id : 9
     * stageName : MPV汽车右后悬挂
     * content : 机械臂将汽车右后悬挂固定在汽车底盘左后部
     * plStepId : 9
     * nextStageId : 10
     */

    private String content;
    private int plStepId;

    @Override
    public String toString() {
        return "StepMsg{" +
                "content='" + content + '\'' +
                ", plStepId=" + plStepId +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPlStepId() {
        return plStepId;
    }

    public void setPlStepId(int plStepId) {
        this.plStepId = plStepId;
    }
}
