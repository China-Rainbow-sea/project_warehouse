package com.example.fruitandvegetableappointmentsapplication;

public class Tuijian {

    private static String[] names={"富士苹果","生菜","香蕉","草莓","橘子"};
    private static String[] news={"苹果口味酸甜，进食后可以刺激唾液、胃酸分泌，有助于缓解口渴症状，还可以增进食欲、促进消化。苹果中含有丰富的果胶，能够促进胃肠道蠕动，还能吸附肠道内的有害物质，调节胃肠道菌群",
            "生菜中含有丰富的膳食纤维，该物质不能被人体吸收，但能够促进肠道蠕动，在肠道内吸收水分，缓解便秘的症状。生菜性凉，具有清热利尿的作用，能够促进体内多余水分排出。",
            "香蕉为芭蕉科植物甘蕉的果实，果肉香甜，除可生食外，还可制成多种加工品。香蕉营养高、热量低，具有降低血压、缓解抽筋、改善便秘等功效。",
            "草莓中有多种氨基酸、微量元素、维生素，能够调节免疫功能，增强机体免疫力。草莓中的花青素、维生素E具有较强的抗氧化能力，有助于延缓衰老、改善皮肤状态。",
            "橘子中含有的维生素C是强抗氧化剂，可以促进氨基酸中酪氨酸和色氨酸的代谢，抑制黑色素沉积，起到延缓衰老和美白的功效。"};

    private static int[] icons={R.drawable.apple,R.drawable.shengcai,R.drawable.xiangjiao,R.drawable.caomei,R.drawable.chengzi};

    public static String[] getNames() {
        return names;
    }

    public static String[] getNews() {
        return news;
    }

    public static int[] getIcons() {
        return icons;
    }
}
