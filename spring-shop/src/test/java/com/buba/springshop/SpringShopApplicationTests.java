package com.buba.springshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;

@SpringBootTest
class SpringShopApplicationTests {
    @Test
    void contextLoads() {
        /*File file = new File("C:/Users/Rainbow/Desktop/a.txt");
        File file2 = new File("C:/Users/Rainbow/Desktop/b.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
            char[] old = new char[104857600];
            int oldIndex = 0;
            char[] strArr = new char[104857600];
            int size = 0;
            boolean flag = false;
            while (true) {
                if (flag) {
                    for (int i = strArr.length - 1; i > 0; i--) {
                        if (strArr[i] == ',') {
                            break;
                        } else {
                            old[i] = strArr[i];
                            oldIndex = i;
                        }
                    }
                }
                if ((size = br.read(strArr, 0, strArr.length)) != -1) {
                    if (flag) {
                        String oldStr = "";
                        for (int i = old.length - 1; i > oldIndex; i--) {
                            oldStr += old[i];
                        }
                        String item = new String(strArr);
                        String newArr = oldStr + item;
                        String[] split = newArr.split(",");
                        for (int i = 0; i < split.length - 1; i++) {
                            bw.write(split[i]);
                            bw.newLine();
                            bw.flush();
                        }
                    } else {
                        String newArr = new String(strArr);
                        String[] split = newArr.split(",");
                        for (int i = 0; i < split.length - 1; i++) {
                            bw.write(split[i]);
                            bw.newLine();
                            bw.flush();
                        }
                    }
                    flag = true;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        long leftCurrentTime = System.currentTimeMillis();
        File file = new File("C:/Users/Rainbow/Desktop/a.txt");
        File file2 = new File("C:/Users/Rainbow/Desktop/b.txt");
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //创建流
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(file2));
            //创建字符缓冲区
            char[] data = new char[100 * 1024 * 1024];
            //统计实际读取的数据条
            int count = 0;
            while ((count = br.read(data, 0, data.length)) != -1) {
                String str = new String(data, 0, count);
                String[] split = str.split(",");
                for (String s : split) {
                    bw.write(s);
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 计算时间
        long rightCurrentTime = System.currentTimeMillis();
        System.out.println("用时：" + (rightCurrentTime - leftCurrentTime) + "毫秒");
    }

    public String to(String from, String to) {
        /*
         * from表示要读取的文件地址
         * to表示要写入的文件地址
         * */
        File file = new File(from);
        File file2 = new File(to);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StreamTokenizer st = new StreamTokenizer(br);
            st.whitespaceChars(',', ',');
            ArrayList<String> list = new ArrayList();
            while (true) {
                int ttype = st.nextToken();
                if ((ttype == StreamTokenizer.TT_WORD) || (ttype > 0)) {
                    list.add(st.sval);
                } else if (ttype == StreamTokenizer.TT_EOF) {
                    break;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

            for (String s : list) {
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }
        return "ok";
    }

}


