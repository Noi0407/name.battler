package magic;

import java.util.Random;

import jobs.BasePlayer;

public class Thunder implements IMagic {

    final static String MAGIC_NAME = "サンダー";
    final static int MAGIC_MP = 20;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        int damage = thunderDamage();

        defender.receiveDamage(damage);
    }

    private int thunderDamage() {
        Random random = new Random();
        return random.nextInt(11) + 20;
    }

}