package magic;

import jobs.BasePlayer;

import java.util.Random;

public class NomalMagic implements IMagic {

    final static String MAGIC_NAME = "通常攻撃";
    final static int MAGIC_MP = 0;

    public void doMagic(BasePlayer attacker, BasePlayer defender) {

        System.out.println(attacker.getName() + "は" + MAGIC_NAME + "を使った。");

        int damage;

        if (luckyHappne(attacker)) {
            luckyMsg();
            damage = attacker.getStr();
        } else {
            damage = attacker.calcDamage(defender);
        }

        defender.receiveDamage(damage);
    }

    private boolean luckyHappne(BasePlayer attacker) {
        Random random = new Random();
        return attacker.getLuck() >= random.nextInt(100) ? true : false;
    }

    private void luckyMsg() {
        System.out.println("会心の一撃!!");
    }

}