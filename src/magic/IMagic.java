package magic;

import jobs.BasePlayer;

public interface IMagic {
    // 定数の情報
    // 攻撃の名前
    String MAGIC_NAME = "";
    // 使用するMP
    int MAGIC_MP = 0;

    // 魔法を行う
    void doMagic(BasePlayer attacker, BasePlayer defender);
}