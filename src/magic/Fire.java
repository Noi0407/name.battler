package magic;

import java.util.Random;

import jobs.BasePlayer;

public class Fire implements IMagic {

    final static String MAGIC_NAME = "ファイア";
    final static int MAGIC_MP = 10;

    @Override
    public void doMagic(BasePlayer attacker, BasePlayer defender) {
        
        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");
        attacker.downMp(MAGIC_MP);

        int damage = fireDamege();

        defender.receiveDamage(damage);
    }

    private int fireDamege() {
        Random random = new Random();
        return random.nextInt(21) + 10;

    }

}