package game;

import game.object.GameObject;

public class Key extends GameObject {
//Ska nyckeln ha koll på vilken container som passar vilken.
    Container container;

    public Key(String name, Boolean moveAble, Container c){
        super(name,moveAble);
        this.container = c;


    }
    public boolean fit (Container c){
        //Kollar om containern har samma namn som nyckeln och då passar den.
        if (this.container.getName().equals(c.getName())){
            return true;
        }
        else{
            return false;
        }
    }
}
