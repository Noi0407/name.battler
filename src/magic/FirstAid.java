package magic;

import jobs.BasePlayer;

public class FirstAid implements IMagic {

    final static String MAGIC_NAME ="応急手当";
    final static int MAGIC_MP = 10;

    final static int UP_HP = 25;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        System.out.println(attacker.getName()+"は"+MAGIC_NAME+"を使った。");
        attacker.downMp(MAGIC_MP);

        attacker.recoveryHp(UP_HP);

    }
    
}