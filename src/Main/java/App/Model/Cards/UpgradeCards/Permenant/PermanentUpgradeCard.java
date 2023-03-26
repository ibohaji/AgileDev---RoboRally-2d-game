package App.Model.Cards.UpgradeCards.Permenant;

import App.Model.Cards.UpgradeCards.UpgradeCard;
import App.Model.Cards.PermanentUpgradeCardEnum;

public class PermanentUpgradeCard extends UpgradeCard {


    private final PermanentUpgradeCardEnum upgradeCardEnum;
    public PermanentUpgradeCard(boolean isPermanent, int cost, PermanentUpgradeCardEnum upgradeCardEnum) {
        super(isPermanent, cost);
        this.upgradeCardEnum = upgradeCardEnum;
    }
}
