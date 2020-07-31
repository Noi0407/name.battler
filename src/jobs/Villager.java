package jobs;

import magic.IMagic;
import magic.NomalMagic;

public class Villager extends BasePlayer {

    IMagic nMagic = new NomalMagic();

    public Villager(String name) {
        super(name);
    }

    @Override
    protected void makeCharacter() {
        // 村人のパラメータを名前から生成する
        this.hp = getNumber(0, 9) + 1;
        this.mp = 0;
        this.str = getNumber(2, 999) + 1;
        this.def = getNumber(3, 9) + 1;
        this.luck = getNumber(4, 90) + 10;
        this.agi = getNumber(5, 60) + 20;
    }
    
    /**
	 * 対象プレイヤーに攻撃を行う
	 * 
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void attack(BasePlayer attacker, BasePlayer defender) {
		System.out.println(this.getName() + "の攻撃！");

		// 通常攻撃を行う
		nMagic.doMagic(attacker, defender);
	}
}