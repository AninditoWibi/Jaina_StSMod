package jaina.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;
import jaina.powers.SpellDamagePower;

public class Evocation extends AbstractJainaCard {

    public static final String ID = IHelper.makeID("Evocation");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public Evocation() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF, JainaEnums.CardTags.ARCANE);
        setMagicNumber(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainPower(new SpellDamagePower(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Evocation();
    }

}