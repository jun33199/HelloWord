    var H0 = "零";
    var H1 = "壹";
    var H2 = "贰";
    var H3 = "叁";
    var H4 = "肆";
    var H5 = "伍";
    var H6 = "陆";
    var H7 = "柒";
    var H8 = "捌";
    var H9 = "玖";

    var HUNDREDMILLION = "亿";
    var TENTHOUSAND = "万";
    var THOUSAND    = "仟";
    var HUNDRED     = "佰";
    var TEN         = "拾";

    var YUAN        = "圆";
    var JIAO        = "角";
    var FEN         = "分";
    var ZHENG       = "整";


    /**
     * 将数字格式的钱转换成文字格式
     * @param money 钱
     * @return 汉字串
     */
    function convert(money)
    {
        var ret = "";

        // 取小数点前面的整钱
        var big = money.split(".")[0];
        if (big != "0" || big != 0)
        {
            // 转换整钱
            ret += makeBig(big);
        }

        // 取小数点后的零钱
        var change =money.split(".")[1];
        if (change == 0 || change == "0")
        {
            if (big == 0 || big == "0")
            {
                ret += H0 + YUAN;
            }
            // 加“整”字
            ret += ZHENG;
        }
        else
        {
            // 转换零钱
            ret += makeChange(change);
        }

        return ret;
    }

    /**
     * 转换小数点钱面的整钱
     * @param money 整钱
     * @return 转换后的汉字
     */
    function makeBig(money)
    {
        var ret = "";
        var part1 = Math.floor(money%10000);                       // 取后四位
        var part2 = Math.floor((money%100000000 - part1)/10000);   // 取前四位
        var part3 = Math.floor(money/100000000);                   // 过亿的数据

        var res1 = "";


        //处理亿以前的数据
        if(part3 > 0 )
        {
            var bRet = makeBig(part3);
            bRet = bRet.substring(0,bRet.length-1) + HUNDREDMILLION;
            ret = ret + bRet;
        }
        // 处理前4位
        if (part2 != 0)
        {
            res1 = format(part2);
            ret = ret + res1 + TENTHOUSAND;
        }

        if (part1 == 0)
        {
            // 后四位为0，返回
            ret += YUAN;
            return ret;
        }

        if (part1 < 1000)
        {
            if (ret != "")
            {
                // 后4位小于1000的话，按习惯要加“零”
                ret += H0;
            }
        }

        // 处理后4位
        var res2 = format(part1);
        ret += res2;
        ret += YUAN;

        return ret;
    }

    /**
     * 将小于一圆的零钱转换成符合习惯的汉字串
     * @param money 零钱
     * @return 转换后的汉字
     */
    function makeChange(money)
    {
        var ret = "";

        var njiao = Math.floor(money / 10);       // 角
        var nfen  = Math.floor(money - njiao*10); // 分

        if (njiao != 0)
        {
            ret += num2Char(njiao) + JIAO;
        }

        if (nfen != 0)
        {
            ret += num2Char(nfen) + FEN;
        }

        return ret;
    }

    /**
     * 将4位数转换成符合习惯的汉字串
     * @param Num4 4位数
     * @return 汉字串
     */
    function format(Num4)
    {
        var ret = "";

        var bit4 = Math.floor(Num4 / 1000); // 取千位
        Num4 -= bit4*1000;
        var bit3 = Math.floor(Num4 / 100);  // 取百位
        Num4 -= bit3*100;
        var bit2 = Math.floor(Num4 / 10);   // 取十位
        Num4 -= bit2*10;
        var bit1 = Math.floor(Num4);        // 取个位

        // 处理千
        if (bit4 != 0)
        {
            ret  = num2Char(bit4) + THOUSAND;
        }

        // 处理百
        if (bit3 != 0)
        {
            ret += num2Char(bit3) + HUNDRED;
        }

        // 处理十
        if (bit2 != 0)
        {
            // 十位不为0
            if ((bit3 == 0) && (ret != ""))
            {
                // 百位是0 且千位不是0，需要加“零”
                ret += H0;
            }
			ret += num2Char(bit2) + TEN;
        }
        else
        {
            // 十位为0
            if ((bit1 != 0) && (ret !=""))
            {
                // 个位不是0 且 千位不是0，需要加“零”
                ret += H0;
            }
        }

        // 处理个
        if (bit1 != 0)
        {
            ret += num2Char(bit1);
        }

        return ret;
    }

    /**
     * 从数字转换成银行所用的汉字
     * @param i 要转换的数字
     * @return  要转换成银行所用的汉字
     */
    function num2Char(i)
    {
        switch(i)
        {
        case 0:
            return H0;
        case 1:
            return H1;
        case 2:
            return H2;
        case 3:
            return H3;
        case 4:
            return H4;
        case 5:
            return H5;
        case 6:
            return H6;
        case 7:
            return H7;
        case 8:
            return H8;
        case 9:
            return H9;
        default:
            return "";
        }
    }   