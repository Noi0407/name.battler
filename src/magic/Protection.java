package magic;

import jobs.BasePlayer;

public class Protection implements IMagic {

    final static String MAGIC_NAME = "プロテクション";
    final static int MAGIC_MP = 10;

    final static int UP_DEF = 10;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        attacker.defenseEnhancement(UP_DEF);
    }
}