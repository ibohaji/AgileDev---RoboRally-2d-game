package App.DTO;

import App.RoborallyApplication.Model.AbCardProgramming;

public class ProgrammingCardDTO implements iFromDTO{

    private AbCardProgramming card;

    public ProgrammingCardDTO(AbCardProgramming abCardProgramming){
        this.card = abCardProgramming;
    }

    @Override
    public AbCardProgramming returnObjectFromDTO() {
        return this.card;
    }

}
