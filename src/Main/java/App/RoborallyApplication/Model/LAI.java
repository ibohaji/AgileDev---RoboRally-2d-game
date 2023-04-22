package App.RoborallyApplication.Model;

import java.util.ArrayList;
import java.util.UUID;

public class LAI implements IToDTO {
    private LGameBrain GameBrain;
    private UUID id;
    private ArrayList<LPlayer> AIPlayers = new ArrayList<>();

    public LAI(LGameBrain GameBrain) {
        this.id = UUID.randomUUID();
        this.GameBrain = GameBrain;
        for (int i = 0; i<this.GameBrain.getPlayers().size(); i++) {
            if (!this.GameBrain.getPlayers().get(i).isHuman()){
                this.AIPlayers.add(this.GameBrain.getPlayers().get(i));
            }
        }
    }
    public void AIAction (LGameBrain GameBrain) {
        for(int i = 0;i<this.AIPlayers.size(); i++) {
            LCardSequence OrderedCardSequence = new LCardSequence(this.AIPlayers.get(i));
            OrderedCardSequence.setCardSequence(this.AIPlayers.get(i).getProgrammingCards());
            this.AIPlayers.get(i).setOrderedCardSequence(OrderedCardSequence);

        }
    }

    @Override
    public String DTOasJson() {
        return null;
    }

    @Override
    public UUID getID() {
        return this.id;
    }
}
