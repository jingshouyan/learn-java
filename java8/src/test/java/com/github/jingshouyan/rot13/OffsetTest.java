package com.github.jingshouyan.rot13;

import org.junit.Test;

import static com.github.jingshouyan.rot13.Rot13UTF16.encode;

public class OffsetTest {

    @Test
    public void offsetEncryption() {
        OffsetEncryption encryption = new OffsetEncryption();
        int[] r1 = new int[]{33, 127, 128, 0xdc00};
        OffsetCalc calc1 = new OffsetCalc(r1, 908);
        encryption.addCalc(calc1);
        int[] r2 = new int[]{55, 104, 985, 0xd000};
        OffsetCalc calc2 = new OffsetCalc(r2, 447);
        encryption.addCalc(calc2);
        String str1 = encryption.encrypt(doc);
        System.out.println(str1);
        String str2 = encryption.decrypt(str1);
        System.out.println(str2);
        assert doc.equals(str2);
    }


    @Test
    public void offsetCalc() {
        int[] range = new int[]{4, 13, 14, 20, 21, 30};
        OffsetCalc offsetCalc = new OffsetCalc(range, 61);
        for (int i = 1; i < 25; i++) {
            int encode = offsetCalc.encode(i);
            int decode = offsetCalc.decode(encode);
            String str = String.format("%d: encode=%d, decode=%d, ok=%b", i, encode, decode, i == decode);
            System.out.println(str);
            assert i == decode;
        }
    }
    @Test
    public void rot13UTF16() {
        String b = encode(doc);
        String c = encode(b);
        String d = encode(c);
        System.out.println(doc);
        System.out.println("-----------");
        System.out.println(b);
        System.out.println("-----------");
        System.out.println(c);
        System.out.println("-----------");
        System.out.println(d);
        System.out.println("-----------");
        System.out.println(doc.equals(c));
        assert doc.equals(c);
        final char[] chars = Character.toChars(0x1F701);

        String s1 = new String(chars);
        String s2 = encode(s1);
        String s3 = encode(s2);
        String s4 = encode(s3);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println("-----------");
        StringBuilder sb = new StringBuilder();
        for (char c1: chars) {
            sb.append(c1);

        }

        System.out.println(sb);
    }

    private final String doc = ("Description\n" +
            "数据加密装置及数据加密方法\n" +
            "\n" +
            "技术领域本发明涉及数据加密装置，特别涉及对于通过测量执行加密处理 时的功率消耗量而解析嵌入在加密模块中的加密密钥的攻击方法来 说安全的数据加密装置。背景技术\n" +
            "\n" +
            "近年来,有人在设想各种以硬件或软件方式实现的加密模块进行 加密处理时的副信息为线索，进行加密密钥的解析的解码法。例如， 在被称为定时攻击的解码方法中，利用加密模块在加密处理上需要的 时间根据在加密处理中使用的加密密钥的值而有尽管很小但是确实 存在的不同，来进行加密密钥的解析。即，在定时攻击中，利用进行 加密处理时的处理时间这样的副信息，从而进行加密密钥的解码。即 使在这样的解码法之中，作为将进行加密处理时的功率消耗量作为副信息进行解码的解码方法，也有人在设想所谓\"简单功耗分析（Simple Power Analysis)\"或\"差分功耗分析（Differential Power Analysis)\" 的各种方法。近年来，已被报告这些解码法还有高性能的测量设备可 便宜地获得的背景，即使对于IC (Integrated Circuit:集成电路）卡 这样的被实施了加密的实际的产品，也能够进行解析。此外，有人在 设想在进行加密处理时，将从加密模块产生的电磁波强度作为副信息 来解码的方法等各种各样的解码方法。在以下的记述中，将以加密处 理时的加密模块的功率消耗量为线索，从而对加密密钥进行解析的解 码方法统称为 <功率解析攻击'。以下，以功率解析攻击为例进行说 明，但对于使用副信息的其他解码方法，也可以同样进行说明。艮口， 本发明不仅适用功率解析攻击，而且只要是利用在加密处理中由加密 模块产生的副信息进行密钥的推定的解码方法，就都适用。\n" +
            "\n" +
            "下面说明有关功率解析攻击的概要。这里，根据对AES\n" +
            "\n" +
            "(Advanced Encryption Standard:高级加密标准）加密（AEC加密的 细节参照非专利文献1)采用功率解析攻击的例子进行说明。图1是 说明AES加密的处理概要的方框图。AES加密支持加密密钥长度为 128bit (位）、192bit、 256bit的三种长度，在以下，以加密密钥长度 为128bit时进行说明。此外，在AES加密中，根据128bit的加密密 钥，生成128bitxl1个被称为轮回密钥(round key) K0、 Kl、…、 K10的密钥，但这里作为轮回密钥已经被生成来说明。即使在图1中 也未图示有关轮回密钥生成处理。(AES加密的处理概要）下面说明AES加密的加密处理概要。AES加密的加密处理，对于128bit的明文P，进行与轮回密钥 K0的每比特的异或运算10a、表变换处理（S) 10b、线性变换（Ll) 10c。接着，用轮回密钥Kl进行与上述同样的一系列的处理 (lla-llc)。进而，依次用轮回密钥K2、 K3、…、K9进行同样的一 系列的处理。但是，对于用轮回密钥K9的一系列的处理，取代线性 变换L1，进行线性变换L2 (19c)。最后，进行与轮回密钥K10的 每比特的异或运算（19d)，将其结果的值作为密文C。(表变换S (10b))下面说明有关表变换10b的处理。再有，表变换llb、 12b、…、 19b也是相同的处理。图2是表示表变换10b的处理的方框图。输入的128bit的数据 从高位起被每8bit分割，成为8bitxl6个的数据。然后，对于各8bit 数据，进行利用表变换Tab的表变换处理（100a〜100p)。这里，表 变换Tab是表示输入8bit和输出8bit之间的对应关系的表，具体地说， 由8bitx256元素的排列Tab[256]表示，输入8bit为X时，利用表变 换的输出值Y可作为Y-Tab[X]获得。具体地说，用于AES加密的表变换是Tab[256]={63h、 7Ch、 77h、 7Bh、…、BBh、 16h)的表。这里，63h的\"h\"表示\"63\"是用16 进制数表示的。此时，对于输入03h的表变换的输出是Tab[2]-77h, 对于输入FEh( 10进制下为254)的表变换的输出成为Tab[254]-BBh。\n" +
            "\n" +
            "这样，进行对于各8bit数据的表变换处理。然后，将各输出结果按与 刚才分割时相同的顺序连结而形成128bit，并作为128bit的输出数据 Y。(线性变换L1 (10c))下面说明有关线性变换L1 (10c)的处理。再有，线性变换llc、 12c、 •••、 18c的处理也是与其同样的处理。线性变换L1按提及的顺序进行被称为ShiftRow的字节转置和被 称为MixColumn的矩阵变换。首先，将输入数据从高位起进行每8bit 的分割，从而形成A0、 Al、 •••、 A14、 A15。字节转置Shi欣ow对 A0〜A15进行排列转换而生成128bit数据。具体地说，从高位起以 A0、 A5、 AIO、 A15、 A4、 A9、 A14、 A3、 A8、 A13、 A2、 A7、 A12、 Al、 A6、 All的顺序连结数据而形成128bit数据。接着，对于上述ShiftRow的结果，进行MixColumn处理。具体 地说，将ShiftRow的结果再次从高位起进行每8bi的t分割，形成 B0、 Bl、…、B14、 B15。接着，X0=B0、 X1=B4、 X2=B8、 X3=B12， 根据下述式1进行矩阵运算，计算YO、 Yl、 Y2、 Y3，从而C0-Y0、 C4=Y1、 C8=Y2、 C12=Y3。[式】1<formula>formula see original document page 6</formula>这里，矩阵运算所使用的加法运算和乘法运算作为扩大体GF (2A8)上的运算进行。此外，\"2A8\"表示\"2的8次方\"。而且， X0=B1、 X1=B5、 X2=B9、 X3=B13，进行上述矩阵运算（式1所示 的运算），计算YO、 Yl、 Y2、 Y3，从而Cl-YO、 C5=Y1、 C9=Y2、 C13=Y3。同样地，X0=B2、 X1=B6、 X2=B10、 X3=B14，进行矩阵 运算（式1)，计算Y0、 Yl、 Y2、 Y3，从而C2=Y0、 C6=Y1、 C10=Y2、 C14=Y3。然后，X0=B3、 X1=B7、 X2=B11、 X3=B15，进行矩阵运\n" +
            "\n" +
            "算，计算Y0、 Yl、 Y2、 Y3，从而C3=Y0、 C7=Y1、 C11=Y2、 C15=Y3。 从高位以这种顺序连结了上述这样获得的CO、 Cl、 C2、…、C14、 C15的128bit数据，成为MixColumn处理的输出数据，即线性变换 Ll的输出数据。(线性变换L2 (19c))线性变换L2 (19c)是从线性变换L1中省略了 MixColumn处理 后的线性变换。即，对于至L2的输入数据，将仅进行了ShiftRow处 理的结果作为L2的输出数据。(加密装置110的结构）下面说明有关将AES加密作为加密装置安装的情况下的一结构例。图3是表示将AES加密作为加密装置安装的情况下的一结构例 的方框图。加密装置110是将明文P加密并输出密文C的装置，包括：寄 存器110a、加密密钥存储部110b、轮回密钥生成部110c、轮回密钥 存储部110d、异或部110e、表保管部110f、表变换部110g、第1线 性变换部110h和第2线性变换部110i。寄存器110a是存储加密处理的中间数据的存储装置。加密密钥 存储部110b是存储加密密钥的存储装置。轮回密钥生成部110c是在 加密处理时，从加密密钥存储部110b读取加密密钥，生成轮回密钥 K0〜K10，并存储在轮回密钥存储部110d中的处理部。异或部110e 是从轮回密钥存储部110d读取必要的轮回密钥，进行异或运算的处 理部。表保管部110f是将上述8bitx256个的数据构成的变换表作为 排列Tab[256]存储的存储装置。表变换部110g是从表保管部110f读 取排列Tab[256],并执行表变换处理的处理部。第1线性变换部110h 和第2线性变换部110i分别是执行上述线性变换Ll和线性变换L2 处理的处理部。接着，说明有关加密装置110的动作。明文P被输入到加密装 置110时，被临时存储在寄存器110a中。接着，异或部110e读取寄 存器110a中所存储的数据，进行与轮回密钥的异或运算，将运算结果盖写存储在寄存器110a中。接着，表变换部110g读取寄存器110a 中所存储的数据，从而进行表变换处理，将变换结果盖写存储在寄存 器110a中。然后，第1线性变换部110h读取寄存器110a中所存储 的数据，从而进行线性变换Ll的处理，将变换结果盖写存储在寄存 器110a中。以下，根据刚才论述的AES加密的处理步骤，重复进行上述处 理。但是，最后的重复中的线性变换处理取代第l线性变换部110h， 进行利用第2线性变换部110i的表变换L2,进而，异或部110e进行 与轮回密钥K10的异或运算，将运算结果盖写存储在寄存器110a中。 然后，加密装置110将寄存器110a中所存储的数据作为密文C输出。(对加密装置110的功率解析攻击）下面说明有关对加密装置110的功率解析攻击的概要。在加密装 置110中，进行AES加密处理的中途的数据（中间数据）被临时存 储在寄存器110a中。即，在图1中，从某个处理块转送给其他处理 块的数据都被临时存储在寄存器110a中后，进行利用其他处理块的 处理。在功率解析攻击中，着眼于上述那样加密处理的中间数据被临时 存储在寄存器的情况。已知将数据存储在寄存器中时的功率消耗量依 赖于存储的数据的内容。例如，存储的数据中\"1\"占据位的数越多， 则存储时消耗的功率越大。而在对寄存器的盖写处理时，寄存器中存 储的数据位的反转发生的位（例如，在位\"1\"被存储的状态下位\"0\" 被盖写的位）越多，存储时消耗的功率越大。利用这些状况，对存储 在寄存器中时的功率消耗量进行测量，从而对寄存器内的数据进行估 计。然后，从估计出的数据值，确定在加密处理中所使用的加密密钥。 例如，图3的异或部110e通过对存储在寄存器110a中时的功率消耗 量进行解析，能够对进行了图1的异或10a的处理后的数据进行解析。 将此时的数据作为D，解析人还知道明文P的值时，则轮回密钥K0 通过K0=D (+) P来求。其中，\"（+)\"表示与\"每个位的异或\"。AES加密的情况下，己知轮回密钥KO与加密密钥相同，所以通过上述解析，加密密 钥被求出。如以上那样可知，从加密处理中的功率消耗量，寄存器中所临时 存储的加密处理的中间值被推测，由此，推测出加密密钥。 (对于功率解析攻击的对策方法）作为对于功率解析攻击的对策方法，公开了被称为掩码 (masking)法的方法（例如，参照专利文献1)。在图3所示的加密 装置110，由于将加密处理的中间值原样临时存储在寄存器110a中， 所以在这种值被解析方面有问题。在掩码法中，具有在将加密处理的 中间值存储在寄存器中之前，利用随机数进行随机化的特征。由此， 即使寄存器的数据被利用功率解析来推测，由于其值用随机数而随机 化，所以不知道实际的加密处理的中间值。因此，轮回密钥的值不能 确定。图4及图5是说明采用了对于AES加密的掩码法的情况下的处 理步骤的方框图。在进行加密处理前，生成128bit的随机数R0〜R10。 然后，将使用了生成的随机数的异或运算20a、 20c、 20e、 21b、…、 29b、 29d、 29h如图4那样追加在原始的AES加密处理中。此时，异 或运算21b、…、29b是与对随机数施加了线性变换L2的结果之间的 异或运算。此时，通过异或运算20a，加密处理的中间值T受到随机 数RO的影响而被随机化。但是，然后通过异或运算20c，再次进行 与随机数RO的异或运算，这种影响被抵消。同样地，利用随机数R1、 R2、…、R10的随机化的影响被抵消，所以最终获得的密文C成为 与通过图1所示的原始的AES加密处理而获得的密文相同的密文。此外，实际上在作为加密装置安装时，不是图4，而是作为图5 的结构安装加密处理。图4和图5的不同在于，将图4中的'与随机 数的异或运算=>表变换^与随机数的异或运算'的一系列处理置换为 '利用随机化过的变换表的表变换'。以下，对这方面进行说明。图6是用于说明图5中的随机化表变换30c的内部结构的方框 图。再有，对于其他的随机化表变换31b、 32b、 •••、 39b，仅使用的 随机数有所不同，与随机化表变换30c是相同的。128bit的输入数据X从高位起进行每8bit分割，成为x0、 xl、…、x15。此外，随机数 R0和Rl从高位起进行每8bit分割，分别成为ROa、 ROb、…、R0p 及Rla、 Rlb、 •••、 Rlp。首先，x0、 xl、…、x15分别进行与R0a、 R0b、…、R0p的异或运算。接着，对于上述结果（各8bit)，分别 进行使用了AES加密的变换表Tab的表变换。然后，对于各表变换 结果，分别进行与Rla、 Rlb、…、Rlp的异或运算，将其结果分别 作为y0、 yl、…、y15。将从高位起以这种顺序连结了 y0、 yl、…、 y15的128bit数据作为Y输出。这里，如果随机数RO(即，R0a〜R0p)和随机数Rl (即，Rla〜Rlp) 被决定，则xO和yO之间的对应关系、xl和yl之间的对应关系、…、 x15和y15之间的对应关系能够作为分别由8bitx256个的数据构成的 变换表来表示。即，图6的处理变成图7所示的利用16种类的变换 表TabOa、 TabOb、…、TabOp的表变换处理303a、 303b、…、303p 组成的处理。将以上归纳时，进行了对于掩码法解码的对策的AES加密的处 理步骤变为图5所示。即，如以下那样。(1) 生成128bitxl1个的随机数RO、 Rl、…、RIO。(2) 根据随机数R0和R1，构成随机化表变换SmO (30c)。具 体地说，如图7所示，生成16种类由8bitx256个数据组成的变换表。 同样地，根据随机数Rl和R2、 R2和R3、…、R9和RIO，构成随 机化表变换Sml〜Sm9。具体的构成方法与SmO相同。(3) 根据图5，进行加密处理。与原始的AES加密处理不同之 处是：与随机数RO的异或运算30a和对随机数R10的线性变换L2 (39e);及追加与上述线性变换的结果的异或运算39f;以及取代原 始的变换表Tab，表变换处理30c、 31b、…、39b使用根据随机数 R0〜R10生成的随机化变换表。非专禾ll文献1: Federal Information ProcessingStandards Publication 197， ' Specification for the ADVANCED ENCRYPTION STANDARD (AES) ， ， November 26， 2001专利文献l:美国专利第6295606号说明书但是，在上述以往技术中，因功率解析攻击对策带来的加密处理 量的增加，有加密装置的处理速度显著下降的技术问题。即，根据生成的随机数所生成的必要的随机化变换表（具有8bitx256个元素的 表）对SmO、 Sml、…、Sm9中的每个包含16种（例如，Sm0的情 况下，为TabOa、 Tab0b、…、Tab0p的16种）。即，在进行1次加 密时，需要合计生成16x10=160种的随机化变换表。此外，随机化变换表的生成所使用的随机数需要是对每次加密处 理有所不同的随机数，上述表生成处理需要在每进行1次加密处理时 加以执行。因此，因用于这种功率解析对策的追加处理，而有加密处 理的处理速度显著地下降的技术问题。此外，用于存储上述160种的随机化变换表的存储器在加密模块 内是必须的，所以还有存储量也显著地增加的技术问题。发明内容本发明是用于解决上述技术问题而完成的发明，目的在于提供一 种数据加密装置，能够阻止功率解析攻击，并且可比以往削减加密处 理的速度降低及存储器量增加。为了解决上述以往的课题，本发明的数据加密装置对明文进行基 于密钥的规定的加密处理，生成密文，其特征在于，包括：随机数生 成部，按每个循环生成一个核心随机数，将规定个数的所述核心随机 数连结，以生成第1随机数；明文数据融合部，进行所述明文与所述 第1随机数之间的数据融合，生成中间数据；以及数据扰乱部，基于 所述第1随机数和第2随机数及所述密钥，按每个循环对所述中间数 据进行数据扰乱处理。根据该结构，第1随机数成为核心随机数的重复。因此，例如， 在核心随机数的个数为16个的情况下，在进行数据扰乱处理的情况 下，能够使以往必需为16个的随机化变换表的个数为一个。因此， 能够削减加密处理的速度下降，并且削减加密处理必需的存储量。例 如，所述数据扰乱部可以构成为，包括：变换表存储部，按每个循环 存储一个变换表，该变换表对将明文以所述规定个数等分后的数据进 行变换；变换表变形部，按每个循环，进行基于所述第1随机数和所 述第2随机数将所述变换表变形的表变换，生成一个变形变换表；变 形变换表存储部，存储所述变形变换表；以及数据变换部，按每个循 环，对所述中间数据，基于所述密钥和所述变形变换表中存储的所述 变形变换表，对于将所述中间数据以所述规定个数等分后的各数据， 进行数据变换处理。此外，也可以是数据加密装置还包括随机数变换部，该随机数变 换部对所述第1随机数执行规定的随机数变换，生成所述第2随机数， 也可以是所述数据扰乱部还包括线性变换部，该线性变换部对所述中 间数据进行规定的线性变换，并输出其结果，所述随机数变换部将所 述线性变换的逆变换作为所述随机数变换来执行。而且，也可以构成为所述规定的加密处理是AES加密处理，所 述线性变换部包括AES加密处理中的InvMixColumn处理和 InvShiftRow处理，所述随机数变换部将所述第1随机数作为第2随 机数输出。而且，也可以是所述核心随机数在全部的循环中为相同的值。通过使核心随机数在全部的循环中相同，能够使数据加密装置所 使用的变形变换表为一个。因此，能够削减加密处理的速度下降，并 且削减加密处理所需的存储器量。再有，本发明不仅能够作为包括了这样的特征性部件的数据加密 装置来实现，而且还能够作为以数据加密装置中所包含的特征性部件 为步骤的数据加密方法来实现，也能够作为使计算机执行数据加密方 法中所包含的特征性步骤的程序来实现。因而，不言而喻，这样的程 序可以通过CD-ROM (Compact Disc-Read Only Memory)等记录媒 体或因特网等通信网络而流通。发明效果根据本发明，能够提供可阻止功率解析攻击、并且与以往相比可 削减加密处理的速度下降及存储量增加的数据加密装置。根据本发明的加密装置，通过将用于对加密处理的中间数据及变 换表进行随机化的随机数根据某个一定的格式、并且以使各表变换处 理之前和之后被异或运算的各个随机数全部为相同的值而进行设定， 从而具有对功率解析攻击那样的利用了从加密处理中加密模块产生 的副信息的解析方法进行阻止、并且使因上述阻止对策造成的加密处 理的速度下降和存储量增加与以往相比被削减的效果。附图说明图1是表示AES加密的处理步骤的方框图。 图2是表示AES加密的表变换处理10b的结构的方框图。 图3是表示AES加密的加密装置110的结构的方框图。 图4是表示现有技术的AES加密的处理步骤的方框图。 图5是表示现有技术的AES加密的处理步骤的方框图。 图6是表示现有技术的随机化表变换处理30c的结构的方框图。 图7是表示现有技术的随机化表变换处理30c的结构的变形例的 方框图。图8是表示本发明的实施方式的AES加密处理步骤的方框图。 图9是表示AES加密的处理步骤的流程图。 图10是表示本发明的实施方式的加密装置410的结构的方框图。 图ll是表示本发明的实施方式的随机数生成部410a的结构的方 框图。图12是表示本发明的实施方式的随机化表变换处理的结构的方 框图。图13是表示本发明的实施方式的随机化表变换处理的第1变形 例的方框图。图14是表示本发明的实施方式的随机化表变换处理的第2变形 例的方框图。图15是说明AES解密的处理步骤的方框图。图16是表示本发明的实施方式的解密装置510的结构的方框图。标号说明410加密装置410a随机数生成部\n" +
            "\n" +
            "410b异或部\n" +
            "\n" +
            "410c加密密钥保管部\n" +
            "\n" +
            "410d轮回密钥生成部\n" +
            "\n" +
            "410e轮回密钥存储部\n" +
            "\n" +
            "410f异或部\n" +
            "\n" +
            "410g寄存器\n" +
            "\n" +
            "410h第1线性逆变换部\n" +
            "\n" +
            "410i表随机化部\n" +
            "\n" +
            "410j随机表存储部\n" +
            "\n" +
            "410k表变换部\n" +
            "\n" +
            "4011表保管部\n" +
            "\n" +
            "401m第1线性变换部\n" +
            "\n" +
            "401n第2线性变换部\n" +
            "\n" +
            "401o第2线性变换部\n" +
            "\n" +
            "410p异或部\n" +
            "\n" +
            "510解密装置\n" +
            "\n" +
            "510a随机数生成部\n" +
            "\n" +
            "510b异或部\n" +
            "\n" +
            "510c加密密钥保管部\n" +
            "\n" +
            "510d轮回密钥生成部\n" +
            "\n" +
            "510e轮回密钥存储部\n" +
            "\n" +
            "510f异或部\n" +
            "\n" +
            "510g寄存器\n" +
            "\n" +
            "510h第1线性逆变换部\n" +
            "\n" +
            "510i逆表随机化部\n" +
            "\n" +
            "510j逆随机表存储部\n" +
            "\n" +
            "510k逆表变换部\n" +
            "\n" +
            "5011逆表保管部\n" +
            "\n" +
            "501m第1线性逆变换部\n" +
            "\n" +
            "501n第2线性逆变换部\n" +
            "\n" +
            "501o第2线性变换部 510p异或部\n" +
            "\n" +
            "具体实施方式\n" +
            "\n" +
            "以下，关于本发明的实施方式，参照附图进行说明。\n" +
            "\n" +
            "(阻止功率解析攻击的AES加密处理步骤的概要）\n" +
            "\n" +
            "图8是说明用于阻止功率解析攻击的AES加密的处理步骤的方\n" +
            "\n" +
            "框图。图9是表示AES加密的处理步骤的流程图。以下，用图8和\n" +
            "\n" +
            "图9说明加密处理。再有，设128bitxll个的轮回密钥K0、 Kl、…、\n" +
            "\n" +
            "K10已经根据128bit的加密密钥生成。\n" +
            "\n" +
            "(1) 生成128bit的随机数（S2)。此外，求对随机数R实施了 线性变换Ll的逆变换Lli所得的值Rl (S4)。进而，求对R1实施 了线性变换L2所得的值R2 (S6)。其中，线性变换L1及L2是与 上述L1、 L2相同的处理。\n" +
            "\n" +
            "(2) 对于128bit的明文P，进行与上述随机数R的异或运算(40a、 S8)。\n" +
            "\n" +
            "(3) 以1=0、 1、…、9的顺序，重复进行以下的处理（（3-1) ~ (3-5))(循环A)。\n" +
            "\n" +
            "(3-1)进行与轮回密钥Ki的异或运算（40b、 41a、 42a、…、49a、 S10)。\n" +
            "\n" +
            "(3-2)进行与随机数R的异或运算（40c、 41b、 42b、…、49b、 S12)。\n" +
            "\n" +
            "(3-3)进行表变换S (40d、 41c、 42c、 •••、 49c、 S14)。其中， 表变换S与已经描述的表变换S相同。\n" +
            "\n" +
            "(34)进行与随机数R1的异或运算（40e、 41d、 42d、 •••、 49d、 S16)。\n" +
            "\n" +
            "(3-5) i=0~8的情况下（S18中为\"是\"），进行线性变换Ll (40f、 41e、 42e、…、48e、 S20) 。 i=9的情况下(S18中为\"否\")， 进行线性变换L2 (49e、 S22)。\n" +
            "\n" +
            "(4) 进行与轮回密钥K10的异或运算（49g、 S24)。\n" +
            "\n" +
            "(5)进行与R2的异或运算（49h、 S26)。\n" +
            "\n" +
            "将对明文P进行了上述（1) ~ (5)的处理之后的结果，作为密 文C输出。此时，（1) 、 （2) 、 （3-2)及（5)的处理是从原始 的AES加密处理所追加的处理。即使追加这样的处理，由以下也可 知能获得与由原始的AES加密处理所获得的密文相同的密文。\n" +
            "\n" +
            "(A) 异或运算40a中在与明文P之间进行异或运算的随机数R 的影响通过其后的异或运算40c而被除去。g卩，与轮回密钥KO的异 或运算40b的处理后的值为P (+) KO (+) R，而利用异或运算40c 的处理后的值变为P (+) KO (+) R (+) R=P (+) KO， R的影响被 抵消。\n" +
            "\n" +
            "(B) 通过表变换40d、 41c、 42c、…、48c之后的异或运算40e、 41d、 42d、 •••、 48d而被异或的随机数R的影响分别通过其后的表变 换41c、 42c、 •••、 49c之前的异或运算41b、 42b、…、49b而被除去。 例如，异或运算40e后的中间值变为（原始AES加密的中间值〉（+) Rl，异或运算41b的输入值变为{原始AES加密的中间值} (+) Ll (Rl)。其中，Ll (Rl)表示实施了线性变换L1的结果，但由于R1 本身是Lli (R)，所以L1 (Rl) =R。艮卩，异或运算40e的输入值变 为（原始AES加密的中间值）（+)R，该R的影响在异或运算41b中， 通过与R的异或运算而被除去。其他的情况也是同样。\n" +
            "\n" +
            "(C) 通过表变换49c之后的异或运算49d而被实施异或运算的 随机数Rl的影响通过其后的异或运算49h而被除去。即，异或运算 49d后的加密处理中间值变为（原始AES加密的中间值）（+) Rl，异 或运算49h中所输入的值变为（原始AES加密的中间值)（+)L2(Rl)。 其中，由于L2 (R1)=R2，所以通过利用异或运算49h的与R2的异 或，上述L2 (Rl)的影响被除去。\n" +
            "\n" +
            "(阻止功率解析攻击的AES加密装置410的结构）\n" +
            "\n" +
            "图10是表示用于实现图8的AES加密处理步骤的加密装置410\n" +
            "\n" +
            "的一例结构的方框图。以下，对于加密装置410的处理步骤，参照图\n" +
            "\n" +
            "9所示的流程图进行说明。\n" +
            "\n" +
            "加密密钥保管部410c保管128bit的加密密钥。轮回密钥生成部\n" +
            "\n" +
            "410d在加密处理时，根据加密密钥保管部410c中保管的加密密钥， 按照AES加密的轮回密钥生成步骤，生成128bitxl1个的轮回密钥 KO、 Kl、…、KIO。轮回密钥存储部410e存储上述轮回密钥KO、 Kl、…、KIO。\n" +
            "\n" +
            "接着，随机数生成部410a生成随机数R (图9的S2)。如图11 所示，随机数生成部410a包括核心随机数生成部411a和随机数扩大 部411b。在随机数R的生成时，首先，核心随机数生成部411a生成 8bit的核心随机数，并传送到随机数扩大部411b。随机数扩大部411b 将连结了十六个上述随机数r所得的值作为随机数R，随机数生成部 410a输出该随机数R。艮P，随机数R变为\n" +
            "\n" +
            "R=r||r||…1Ir (r的个数为16个） 其中，\"II\"表示数据的连结。生成的128bit的随机数R传送到异或 部410b、表随机化部410i、第l线性逆变换部4I0h。\n" +
            "\n" +
            "第1线性逆变换部410h对上述随机数R实施线性变换Ll的逆 变换，从而求R1 (图9的S4)，传送到表随机化部410i及第2线性 变换部4100。第l线性逆变换部410h的详细步骤如以下述那样。首 先，将输入数据R从高位起进行每8bit分割，成为aO、 al、 a2、…、 a15。接着，xO=aO、 xl=al、 x2=a2、 x3=a3，进行以\n" +
            "\n" +
            "[式2]\n" +
            "\n" +
            "<formula>formula see original document page 17</formula>\n" +
            "\n" +
            "表示的GF (2A8)上的矩阵运算，求bO、 bl、 b2、 b3。使它为yO斗O、 yl=bl、 y2=b2、 y3=b3。同样地，x0-a4、 xl=a5、 x2=a6、 x3=a7，通 过上述矩阵运算，求bO、 bl、 b2、 b3，获得y4-b0、 y5=bl、 y6=b2、 y7=b3。而且，x0=a8、 xl=a9、 x2=al0、 x3=all，求y8-b0、 y9=bl、 ylO=b2、 yll=b3，获得x0-al2、 xl=al3、 x2=al4、 x3=al5，求yl2^0、 yl3=bl、 yl4=b2、 yl5=b3。将这样求出的y0、 yl、…、y15从高位 起以yO、 y13、 y10、 y7、 y4、 yl、 y14、 yll、 y8、 y5、 y2、 yl5、 yl2、\n" +
            "\n" +
            "y9、 y6、 y3的顺序连结的128bit数据作为第1线性逆变换部410h的 输出。其中，在上述矩阵运算中，x0^1^2^3^时，y0、 yl、 y2、 y3被如下计算。但是，乘法运算、加法运算都是GF (2A8)上的运算。 yO=y 1 =y2=y3=0Ehxr+0BhxrH)DhxrH)9hxr\n" +
            "\n" +
            "=01hxr\n" +
            "\n" +
            "=r\n" +
            "\n" +
            "同样地，由于y4〜yl5也示出都与r相等，所以第1线性逆变换 部410h的输出Rl表示为Rl-R^llrH…llr。艮卩，第1线性逆变换 部410h实际上不进行任何处理即可。\n" +
            "\n" +
            "接着，表随机化部410i根据随机数R和Rl对表保管部4101中 保管的原始AES加密的变换表Tab进行随机化。如果是现有技术， 则如图6所示那样，在各表变换处理300a〜300p之前与之后，被实施 了利用各自不同的随机数的异或运算。由此，需要生成图7所示的各 自不同的随机化变换表303a〜300p。但是，在本实施方式中，如已经 表示那样，随机数R及Rl都为R^R^IIrl1…1Ir。由此，如图12 所示，在各表变换处理412a〜412p之前与之后，被实施全部利用相同 随机数r的异或运算。因此，这些一系列的处理（利用随机数的异或 ^表变换^利用随机数的异或）如图13所示那样，变形为全部相同 的随机化变换表mTab。即，图13能够如14那样通过一个随机化变 换表mTab而实现。具体地说，如以下那样生成随机化变换表mTab。 首先，表随机化部410i读取在表保管部4101中保管的原始AES加密 的变换表Tab的排列数据Tab[0]〜Tab[255]。接着，使用上述排列数 据和随机数R及随机数Rl中包含的核心随机数r生成以下那样的排 列数据mTab[0]〜mTab[255]。\n" +
            "\n" +
            "mTab[i]=Tab[i (+) r] (+) r\n" +
            "\n" +
            "表随机化部410i将上述那样生成的随机化变换表mTab传送到 随机表存储部410j，并将其存储。\n" +
            "\n" +
            "进而，第2线性变换部410o对随机数Rl实施线性变换L2，求 R2，并传送到异或部410p (图9的S6)。这里，线性变换L2如已 经描述的那样，是字节单位的置换，并且随机数R1如已经说明的那样，Rl=R=r||r||…1Ir(r为8bit)，所以L2 (Rl) =R1=R。艮P，实 际上，第2线性变换部410o不进行任何处理即可。以上是用于进行加密处理的前处理，在以下说明中，根据上述前 处理结果，说明用于进行对明文P的加密处理的步骤。对输入到加密装置410的明文P,异或部410b进行与上述随机 数R的异或运算，将其结果传送到寄存器410g，并临时存储（图9 的S8)。接着，异或部410f读取寄存器410g内的数据，从而进行与轮回 密钥存储部410e中存储的轮回密钥K0的异或运算，将结果写入寄存 器410g (图9的S10)。此时，在写入前寄存器中存储的数据被盖写 而删除。接着，表变换部410k读取寄存器410g内的数据，基于随机表存 储部410j中存储的随机化变换表mTab进行表变换处理（图9的 S12-S16)。具体地说，如果从寄存器410g读取的数据为X，则表变 换处理后的数据Y作为Y=mTab[X]而求出。变换处理后的数据盖写在寄存器410g中。盖写前的寄存器 410g内的数据被删除。接着，第1线性变换部410m读取寄存器410g的数据，从而实 施线性变换L1，并盖写在寄存器410g中（图9的S18为\"是\"，S20)。上述处理后，用轮回密钥K1、 K2、 K3、…、K9重复进行以上 的异或部410f、表变换部410k、第1线性变换部410m的一系列的处 理（图9的循环A)。但是，仅在用轮回密钥K9进行上述一系列的 处理时，取代第1线性变换部410m的处理，进行第2线性变换部410n 的线性变换L2的处理（图9的S18为\"否\"，S22)。进而，异或部410f读取寄存器410g内的数据，从而进行与轮回 密钥存储部410e中存储的轮回密钥K10的异或运算，将结果写入寄 存器410g (图9的S24)。然后，异或部410p读取在寄存器410g中存储的数据，从而进行 与随机数R2的异或运算，将其结果作为密文C从加密装置410输出(图9的S26)。(AES解密处理步骤的概要）下面说明对由以上说明的阻止功率解析攻击的AES加密处理所 生成的密文C进行解密的处理步骤。图15是说明AES解密的处理步骤的方框图。与图8所示的加密 处理步骤的不同是，为了从密文生成明文P，数据的流动变得相反。此外，不同点在于，取代表变换S而实施表变换S的逆变换Si， 取代线性变换U而实施线性变换Ll的逆变换Lli，取代线性变换 L2而实施线性变换L2的逆变换L2i。但是，关于线性变换L2 (48f)， 仍然不变。(解密装置510的结构）图16是表示对由加密装置410加密过的密文C进行解密而求明 文P的解密装置510的结构的方框图。这里，仅说明与加密装置410 的不同部分。逆表保管部510保管与在表保管部4101中保管的变换表Tab为 逆变换关系的逆变换表ITab。具体地说，ITab的排列元素 ITab[0]〜ITab[255]被如下定义。ITab[Tab[i]H (i=0、 1、…、255)逆表随机化部510i根据逆变换表ITab求随机化逆变换表ImTab 的方法与表随机化部410i中所述的方法相同，所以省略。在解密装置510的解密处理中，首先，进行异或部510p的处理。 该处理是与异或部410p相同的处理。接着，与异或部410f同样，异 或部510f进行使用了轮回密钥K10的异或运算。然后，以轮回密钥 K9、 K8、…、Kl、 KO的顺序用轮回密钥重复进行第1线性逆变换部 510m、逆表变换部510k、异或部510f的顺序组成的一系列处理。此 时，在使用了轮回密钥K9的情况下的一系列处理中，取代第l线性 逆变换部510m，进行第2线性逆变换部510n的处理。此时，第1线 性逆变换部510m进行已描述的线性变换Ll的逆变换处理Lli，第2 线性逆变换部510n进行线性变换L2的逆变换处理L2i。上述处理之 后，异或部510b进行异或运算，将其结果作为明文P输出。\n" +
            "\n" +
            "如以上那样，在本实施方式中，仅生成并存储一个8bitx256个 排列元素组成的随机化变换表即可。在现有技术中，由于需要生成 160个8bitx256个排列元素组成的随机化变换表，所以用于生成随机 化变换表的处理量与现有技术相比被削减到160分之一。再有，在本实施方式中，将作为功率解析攻击对策的对象的加密 作为AES加密，但不仅限于AES加密，只要是与轮回密钥的数据融 合、表变换、线性变换这样的一系列处理组成的重复式的加密方式， 就可以利用同样的方法来应用。作为这样的加密方式的例子，有 Camellia加密、Hierocrypt加密等。此外，在本实施方式中，作为将两个数据进行数据融合的方法， 使用了异或运算，但运算也可以是算术加法运算。此外，在本实施方式中，第l线性逆变换部410m及第2线性逆 变换部410n实际上不进行任何处理也可以，所以也可以省略它们。再有，基于上述实施方式说明了本发明，但本发明当然不限定于 上述实施方式。以下的情况也包含在本发明中。(1) 上述各装置具体地说，是由微处理器、ROM、 RAM、硬盘 单元、显示单元、键盘、鼠标等构成的计算机系统。上述RAM或硬 盘单元中，存储有计算机程序。上述微处理器通过按照上述计算机程 序而动作，从而各装置实现其功能。这里，计算机程序是为了实现规 定功能而将表示对计算机的指令的命令码组合多个而构成的程序。(2) 构成上述各装置的构成要素的一部分或全部也可以由一个系 统LSI (Large Scale Integration:大规模集成电路）构成。系统LSI 是将多个结构部集成在一个芯片上所制造的超多功能LSI,具体地说， 是包含微处理器、ROM、 RAM等而构成的计算机系统。上述RAM 中，存储有计算机程序。上述微处理器通过按照上述计算机程序而动 作，从而系统LSI实现其功能。G)构成上述各装置的结构要素的一部分或全部也可以由在各装 置上可装拆的IC卡或单体模块构成。上述IC卡或上述模块是由微处 理器、ROM、 RAM等构成的计算机系统。上述IC卡或上述模块也 可以包含上述超多功能LSI。微处理器通过按照计算机程序而动作， 从而上述IC卡或上述模块实现其功能。这种IC卡或这种模块最好是具有抗篡改性。(4) 本发明也可以是上述所示的方法。此外，可以是将这些方法 通过计算机来实现的计算机程序，也可以是上述计算机程序组成的数 字信号。此外，本发明也可以是，将上述计算机程序或上述数字信号记录 在计算机可读取的记录媒体、例如软盘、硬盘、CD-ROM、 MO、 DVD、 DVD-ROM、 DVD—RAM、 BD (Blu-ray Disc:蓝光光盘）、半导体 存储器等中的形式。此外，也可以是这些记录媒体中所记录的上述数 字信号。此外，本发明也可以是，将上述计算机程序或上述数字信号经由 电通信线路、无线或有线通信线路、以因特网为代表的网络、数据广 播等而传输的形式。此外，本发明也可以是包括微处理器和存储器的计算机系统，上 述存储器存储有上述计算机程序，上述微处理器按照上述计算机程序 而动作。此外，也可以是通过将上述程序或上述数字信号记录在上述记录 媒体中而转送，或将上述程序或上述数字信号经由上述网络等转送， 由独立的其他计算机系统实施。(5) 也可以将上述实施方式及上述变形例分别组合。 工业上的可利用性本发明的加密装置具有阻止功率解析攻击，并且与现有技术相比 削减加密处理的处理量的特征，所以本发明可适用于高速处理或寻求 以低成本实现的加密装置。"
    ).replace("\n","");

}
