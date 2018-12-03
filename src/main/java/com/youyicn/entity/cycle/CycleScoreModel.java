package com.youyicn.entity.cycle;


/**
 * <p>
 *
 * </p>
 *
 * @author kaifa
 * @since 2018-11-27
 */
public class CycleScoreModel{

    /**
     * 评分模板Id
     */
    private Integer modelId;

    /**
     * 构建人Id
     */
    private Integer userId;

    /**
     * 模板名称
     */
    private String modelName;

    /**
     * 评分项
     */
    private String scoreItem;

    /**
     * 备注
     */
    private String remark;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(String scoreItem) {
        this.scoreItem = scoreItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CycleScoreModel{" +
                "modelId=" + modelId +
                ", userId=" + userId +
                ", modelName='" + modelName + '\'' +
                ", scoreItem='" + scoreItem + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
