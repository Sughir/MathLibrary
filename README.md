# MathLibrary
複素数・行列演算や、多倍長に対応した三角関数等のライブラリ (試作)

2003年頃に作った複素数、行列演算、多倍長 (BigDecimal、BigInteger) に対応したライブラリです。
三角関数や対数等にも対応していますが多倍長については試作が多くバグが多数残されている場合があります。

MATLAB https://jp.mathworks.com/products/matlab.html と、JAMA : A Java Matrix Package http://math.nist.gov/javanumerics/jama/ を見て作りました。2003年頃の当時はJAMAのライブラリは行列と複素数とBigInteger/BigDecimalに同時に対応したライブラリがないこと、三角関数や対数等に対応したライブラリがないことから作り始めました。

しかし、こちらは更新が停滞し、Apache Commons Math http://commons.apache.org/proper/commons-math/ 
が徐々に進化してゆき頻繁にアップデートされ、現在ではより優れたライブラリがオープンソースとして手に入るので、そちらを優先して使用することをおすすめします。
