//package com.github.jingshouyan;
//
//import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//
///**
// * @ClassName EncryptTest
// * @Description TODO
// * @Author 37052
// * @Date 2020-08-04 11:17
// * @Version 1.0
// **/
//public class EncryptTest {
//
//  private static final String key = "lianghuilonglong"; // 私钥 AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
//
//  private static final String iv = "aabbccddeeffgghh"; // 初始化向量参数
//  /**
//   * @Author Ray
//   * @Description 加密
//   * @Date 2020-08-04 11:17
//   * @Param []
//   * @return java.lang.String
//   */
//  public static String encrypt(String text){
//
//    Key keySpec = new SecretKeySpec(key.getBytes(), "AES"); // 参数1： 私钥字节，参数2：加密方式
//
//    IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
//
//    // 实例化加密类
//    try {
//      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//      // 初始化，此方法可以采用三种方式，按服务器要求来添加。
//      //（1）无第三个参数
//      // cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//      //（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)
//      // SecureRandom random = new SecureRandom();
//      //（3）采用此代码中的IVParameterSpec
//      // cipher.init(Cipher.ENCRYPT_MODE, keySpec, random);
//
//      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
//
//      //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,
//      byte [] b = cipher.doFinal(text.getBytes());
//
//      String ret = Base64.encode(b);
//
//      return ret;
//    } catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    } catch (NoSuchPaddingException e) {
//      e.printStackTrace();
//    }catch (InvalidKeyException e) {
//      e.printStackTrace();
//    } catch (InvalidAlgorithmParameterException e) {
//      e.printStackTrace();
//    }catch (IllegalBlockSizeException e) {
//      e.printStackTrace();
//    } catch (BadPaddingException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  /**
//   * @Author Ray
//   * @Description 先将加密字符串按照Base64、HEX等解码成byte[]， 再用加密时相同的加密方式及key进行解密。
//   * 加密与解密代码几乎相同。唯一区别为在Cipher类init时，工作模式为Cipher.DECRYPT_MODE
//   * @Date 2020-08-04 11:32
//   * @Param [text]
//   * @return java.lang.String
//   */
//  public static String decrypt(String text){
//    byte[] bytes = null;
//    Cipher cipher = null;
//    byte [] b = new byte[0];
//    try {
//      bytes = Base64.decode(text);
//
//      Key keySpec = new SecretKeySpec(key.getBytes(), "AES"); // 参数1： 私钥字节，参数2：加密方式
//
//      IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
//
//      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//
//      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
//
//      b = cipher.doFinal(bytes);
//
//      return new String(b, "UTF-8");
//
//    } catch (Base64DecodingException e) {
//      e.printStackTrace();
//    }catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    } catch (IllegalBlockSizeException e) {
//      e.printStackTrace();
//    } catch (BadPaddingException e) {
//      e.printStackTrace();
//    } catch (InvalidKeyException e) {
//      e.printStackTrace();
//    } catch (InvalidAlgorithmParameterException e) {
//      e.printStackTrace();
//    }catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    } catch (NoSuchPaddingException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  /**
//   * @Author Ray
//   * @Description 对称加解密
//   * @Date 2020-08-04 11:45
//   * @Param [text, direction, privateKey, ivKey]
//   * @return java.lang.String
//   */
//  private static String SymmetryCipher(String text, int direction, String privateKey, String ivKey) throws Exception{
//    if(direction != Cipher.DECRYPT_MODE && direction != Cipher.ENCRYPT_MODE){
//      throw new Exception("direction is not correct. direction only can be set Cipher.DECRYPT_MODE or  Cipher.ENCRYPT_MODE");
//    }
//    // 私钥 AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
//    String key = privateKey == null ? "lianghuilonglong": privateKey;
//    // 初始化向量参数
//    String iv = ivKey == null ? "aabbccddeeffgghh": ivKey;
//
//    byte[] bytes = null;
//    try {
//      if(direction == Cipher.DECRYPT_MODE){
//        bytes = Base64.decode(text);
//      }else{
//        bytes = text.getBytes();
//      }
//
//      Key keySpec = new SecretKeySpec(key.getBytes(), "AES"); // 参数1： 私钥字节，参数2：加密方式
//
//      IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
//
//      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//      // 这里direction只有两种， Cipher.DECRYPT_MODE Cipher.ENCRYPT_MODE
//      cipher.init(direction, keySpec, ivSpec);
//
//      byte [] b = cipher.doFinal(bytes);
//
//      if(direction == Cipher.DECRYPT_MODE){
//        return new String(b, "UTF-8");
//      }else{
//        return Base64.encode(b);
//      }
//
//    } catch (Base64DecodingException e) {
//      e.printStackTrace();
//    }catch (UnsupportedEncodingException e) {
//      e.printStackTrace();
//    } catch (IllegalBlockSizeException e) {
//      e.printStackTrace();
//    } catch (BadPaddingException e) {
//      e.printStackTrace();
//    } catch (InvalidKeyException e) {
//      e.printStackTrace();
//    } catch (InvalidAlgorithmParameterException e) {
//      e.printStackTrace();
//    }catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    } catch (NoSuchPaddingException e) {
//      e.printStackTrace();
//    }
//    return "";
//  }
//
//  public static void main(String[] args){
//    String text = "abcdefg";        // 待加密信息
//    String encText = encrypt(text);
//    String decText = decrypt(encText);
//
//    System.out.println(text);
//    System.out.println(encText);
//    System.out.println(decText);
//
//    try {
//      encText = SymmetryCipher(text, Cipher.ENCRYPT_MODE, ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M", null);
//      System.out.println(encText);
//      System.out.println(SymmetryCipher(encText, Cipher.DECRYPT_MODE, ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M", null));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}
