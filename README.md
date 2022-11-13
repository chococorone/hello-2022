# hello-2022
お試しに書いたコードをpushしていきたい

## RxJavaTest
* Android Java
* RxJava,RxAndroidで非同期処理を試した
* retrofit2 + gsonの流行りな組み合わせでAPI叩いてみた
  * 郵便番号検索APIを試しに叩いてみた
    * http://zipcloud.ibsnet.co.jp/doc/api


## ContentProviderTest
* Android Java
* Room を試した
  * https://developer.android.com/training/data-storage/room?hl=ja#java
  * rxjavaで非同期にroomで用意したインターフェースを叩いてみた
* Room + ViewModel + Databindingを試した
  * view層は、データの更新で何が行われているかを関知せず、渡されたデータをただ描く
* RecyclerView + Databindingを試した
  * Adapterは公式が用意してくれているListAdapterを継承して作ってみた
    * 実装が楽になるらしいが、楽になる前を知らないのでよくわからず
  * viewHolderは個々のviewのbindingを持つ(databindingしてないときは個々のviewの参照を持つ)
  * AdapterはviewHolderを作ったり、返したり

* RecyclerViewで表示しているリストを、searchViewに入れた文字列で部分一致検索絞り込みしてみた
  * SearchViewを新たに追加して、そのクリックリスナーでdaoで定義している部分一致検索を実行
    * 実行結果はviewmodelで保持しているMutableLiveDataに格納することで、自動でViewに反映される

### py/html-parse.py
* chromeのブックマークエクスポートしてでてきたhtmlファイルをparseしてdbファイルにしてみた
  * Roomで読みこむための材料にした