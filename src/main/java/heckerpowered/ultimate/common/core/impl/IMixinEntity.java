package heckerpowered.ultimate.common.core.impl;

public interface IMixinEntity {
    boolean isUltimateDead();

    void setUltimateDead();

    int getUltimateDeathTime();

    void setUltimateDeathTime(final int ticks);
}
