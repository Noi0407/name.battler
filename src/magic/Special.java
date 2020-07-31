package magic;

import jobs.BasePlayer;

public class Special implements IMagic {

    final static String MAGIC_NAME ="必殺技";
    final static int MAGIC_MP =0;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        defender.receiveDamage(attacker.getStr());
    }
}