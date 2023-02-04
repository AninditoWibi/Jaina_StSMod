package jaina.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.powers.BurningPower;

import java.util.ArrayList;


public class Scorch extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Scorch");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Scorch() {
        super(ID, false, CARD_STRINGS, COST, CardType.SKILL, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY, JainaEnums.CardTags.FIRE);
        setMagicNumber(4);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        givePower(new BurningPower(m, magicNumber), magicNumber);
        upgradeAllIgnite(AbstractDungeon.player.hand.group);
        upgradeAllIgnite(AbstractDungeon.player.discardPile.group);
        upgradeAllIgnite(AbstractDungeon.player.drawPile.group);
        upgradeAllIgnite(AbstractDungeon.player.exhaustPile.group);
    }

    /**
     * 升级所有【灼烧】
     *
     * @param cards 卡牌列表
     */
    private void upgradeAllIgnite(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c instanceof Scorch) {
                c.baseMagicNumber += 2;
                c.magicNumber = c.baseMagicNumber;
                c.applyPowers();
                c.initializeDescription();
            }
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new Scorch();
    }

}
