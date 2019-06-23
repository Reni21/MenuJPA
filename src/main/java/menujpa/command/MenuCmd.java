package menujpa.command;

import menujpa.service.DishService;

public abstract class MenuCmd {
    protected DishService dishService;
    public MenuCmd(DishService dishService) {
        this.dishService = dishService;
    }

   public abstract void execute();

}
