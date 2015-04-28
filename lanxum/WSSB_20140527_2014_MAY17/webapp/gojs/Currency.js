    var H0 = "��";
    var H1 = "Ҽ";
    var H2 = "��";
    var H3 = "��";
    var H4 = "��";
    var H5 = "��";
    var H6 = "½";
    var H7 = "��";
    var H8 = "��";
    var H9 = "��";

    var HUNDREDMILLION = "��";
    var TENTHOUSAND = "��";
    var THOUSAND    = "Ǫ";
    var HUNDRED     = "��";
    var TEN         = "ʰ";

    var YUAN        = "Բ";
    var JIAO        = "��";
    var FEN         = "��";
    var ZHENG       = "��";


    /**
     * �����ָ�ʽ��Ǯת�������ָ�ʽ
     * @param money Ǯ
     * @return ���ִ�
     */
    function convert(money)
    {
        var ret = "";

        // ȡС����ǰ�����Ǯ
        var big = money.split(".")[0];
        if (big != "0" || big != 0)
        {
            // ת����Ǯ
            ret += makeBig(big);
        }

        // ȡС��������Ǯ
        var change =money.split(".")[1];
        if (change == 0 || change == "0")
        {
            if (big == 0 || big == "0")
            {
                ret += H0 + YUAN;
            }
            // �ӡ�������
            ret += ZHENG;
        }
        else
        {
            // ת����Ǯ
            ret += makeChange(change);
        }

        return ret;
    }

    /**
     * ת��С����Ǯ�����Ǯ
     * @param money ��Ǯ
     * @return ת����ĺ���
     */
    function makeBig(money)
    {
        var ret = "";
        var part1 = Math.floor(money%10000);                       // ȡ����λ
        var part2 = Math.floor((money%100000000 - part1)/10000);   // ȡǰ��λ
        var part3 = Math.floor(money/100000000);                   // ���ڵ�����

        var res1 = "";


        //��������ǰ������
        if(part3 > 0 )
        {
            var bRet = makeBig(part3);
            bRet = bRet.substring(0,bRet.length-1) + HUNDREDMILLION;
            ret = ret + bRet;
        }
        // ����ǰ4λ
        if (part2 != 0)
        {
            res1 = format(part2);
            ret = ret + res1 + TENTHOUSAND;
        }

        if (part1 == 0)
        {
            // ����λΪ0������
            ret += YUAN;
            return ret;
        }

        if (part1 < 1000)
        {
            if (ret != "")
            {
                // ��4λС��1000�Ļ�����ϰ��Ҫ�ӡ��㡱
                ret += H0;
            }
        }

        // �����4λ
        var res2 = format(part1);
        ret += res2;
        ret += YUAN;

        return ret;
    }

    /**
     * ��С��һԲ����Ǯת���ɷ���ϰ�ߵĺ��ִ�
     * @param money ��Ǯ
     * @return ת����ĺ���
     */
    function makeChange(money)
    {
        var ret = "";

        var njiao = Math.floor(money / 10);       // ��
        var nfen  = Math.floor(money - njiao*10); // ��

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
     * ��4λ��ת���ɷ���ϰ�ߵĺ��ִ�
     * @param Num4 4λ��
     * @return ���ִ�
     */
    function format(Num4)
    {
        var ret = "";

        var bit4 = Math.floor(Num4 / 1000); // ȡǧλ
        Num4 -= bit4*1000;
        var bit3 = Math.floor(Num4 / 100);  // ȡ��λ
        Num4 -= bit3*100;
        var bit2 = Math.floor(Num4 / 10);   // ȡʮλ
        Num4 -= bit2*10;
        var bit1 = Math.floor(Num4);        // ȡ��λ

        // ����ǧ
        if (bit4 != 0)
        {
            ret  = num2Char(bit4) + THOUSAND;
        }

        // �����
        if (bit3 != 0)
        {
            ret += num2Char(bit3) + HUNDRED;
        }

        // ����ʮ
        if (bit2 != 0)
        {
            // ʮλ��Ϊ0
            if ((bit3 == 0) && (ret != ""))
            {
                // ��λ��0 ��ǧλ����0����Ҫ�ӡ��㡱
                ret += H0;
            }
			ret += num2Char(bit2) + TEN;
        }
        else
        {
            // ʮλΪ0
            if ((bit1 != 0) && (ret !=""))
            {
                // ��λ����0 �� ǧλ����0����Ҫ�ӡ��㡱
                ret += H0;
            }
        }

        // �����
        if (bit1 != 0)
        {
            ret += num2Char(bit1);
        }

        return ret;
    }

    /**
     * ������ת�����������õĺ���
     * @param i Ҫת��������
     * @return  Ҫת�����������õĺ���
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