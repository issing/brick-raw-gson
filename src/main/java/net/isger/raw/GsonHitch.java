package net.isger.raw;

/**
 * 谷歌GSON便车
 * 
 * @author issing
 * 
 */
public class GsonHitch {

    /**
     * 免费便车
     * 
     * @param source
     */
    public static void hitch(Object source) {
        // 亲，要看好巴士，别搭错车（^o^）
        if (!(source instanceof Depository)) {
            return;
        }
        Depository.addWrapper(new GsonWrapper());
    }

}
