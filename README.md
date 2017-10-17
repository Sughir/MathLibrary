# MathLibrary
複素数・行列演算や、多倍長に対応した三角関数等のライブラリ (試作)

2003年頃に作った複素数、行列演算、多倍長 (BigDecimal、BigInteger) に対応したライブラリです。
三角関数や対数等にも対応していますが多倍長については試作が多くバグが多数残されている場合があります。

MATLABと、JAMA : A Java Matrix Package http://math.nist.gov/javanumerics/jama/ を見て作りました。
しかしこちらは更新が停滞し、現在では Apache Commons Math http://commons.apache.org/proper/commons-math/ 
が頻繁にアップデートされ、より優れたライブラリがオープンソースで手に入るので、そちらを優先して使用することをおすすめします。
