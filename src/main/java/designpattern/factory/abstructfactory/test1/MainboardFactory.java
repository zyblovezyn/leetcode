package designpattern.factory.abstructfactory.test1;

public class MainboardFactory {
    public static Mainborad createMainboard(int type){
        Mainborad mainborad=null;
        if(type==1){
            mainborad=new IntelMainboard(755);
        }else if(type==2){
            mainborad=new AmdMainboard(938);
        }
        return mainborad;
    }
}
