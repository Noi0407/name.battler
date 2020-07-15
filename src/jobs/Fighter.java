package jobs;

import magic.IMagic;
import magic.NomalMagic;

// プレイヤー：戦士
public class Fighter extends BasePlayer {

	IMagic nMagic = new NomalMagic();

	public Fighter(String name) {
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {
		// 戦士のパラメータを名前から生成する
		this.hp = getNumber(0, 200) + 100;
		this.mp = 0;
		this.str = getNumber(2, 70) + 30;
		this.def = getNumber(3, 70) + 30;
		this.luck = getNumber(4, 99) + 1;
		this.agi = getNumber(5, 49) + 1;
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
