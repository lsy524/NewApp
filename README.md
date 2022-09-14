## 원티드 프리온보딩 안드로이드 코스 사전과제
> News API를 활용한 Android 뉴스앱 개발

## 프로젝트 요구사항

* [News API](https://newsapi.org/) 활용
* UI 개발은 XML 기반은 View를 사용해야 한다.
    * Jetpack Compose는 사용할 수 없다.


* News 목록
    * [top-headlines API](https://newsapi.org/docs/endpoints/top-headlines)를 활용해 뉴스 목록을 나타내는 화면
    * 뉴스 정보를 표기
    * 뉴스를 클릭하면 상세 내용을 볼 수 있어야 한다.

* News 카테고리
    * [top-headlines API](https://newsapi.org/docs/endpoints/top-headlines)를 활용해 조회할 수 있는 카테고리를 나타내는 화면
    * 카테고리를 클릭하면 해당 카테고리의 News 목록을 볼 수 있어야 한다.

* News 상세정보
    * 선택한 News의 상세 정보를 나타내야 한다.
    * 별모양 아이콘 클릭시 on/off 상태가 변경되어야 한다.
    * 별모양 아이콘이 on 상태인 경우 기사를 저장하며, Saved 탭 목록에서 확인할 수 있어야 한다.
    * 별모양 아이콘이 off 상태로 변경되면 기사를 삭제하고, Saved 탭 목록에서 볼 수 없어야 한다.
    * 별모양 아이콘의 상태는 앱을 종료하고 다시 실행한 후에도 유지되어야 한다.

* Saved 목록
    * 저장한 News 목록을 나타나야 한다.
    * 데이터베이스 구현은 SQLiteOpenHelper 또는 Room을 사용해야 한다.

## 프로젝트 데모
|목록|카테고리|북마크|
|------|---|---|
|![main](https://user-images.githubusercontent.com/96644159/190138439-924476fb-e334-4223-aa08-b14eb31a9491.gif)|![category](https://user-images.githubusercontent.com/96644159/190138708-022f9c6e-3bdb-4eef-9fa4-7ef90f53db99.gif)|![saved](https://user-images.githubusercontent.com/96644159/190138855-d483688f-0172-4c0d-b1de-0c32402dc360.gif)
|


## 파일 구조
```bash
├── activities
│   └── CategoryNewsActivity.kt
│   └── MainActivity.kt
│   └── NewsDetailsActivity.kt
│   └── SavedNewsDetailsActivity.kt
├── adapter
│   └── NewsAdapter.kt
│   └── SavedAdapter.kt
├── fragment
│   └── CategoryFragment.kt
│   └── NewsFragment.kt
│   └── SavedFragment.kt
├── models
│   └── NewsResponse.kt
├── network
│   └── TopNewsService.kt
├── room
│   └── SavedApp.kt
│   └── SavedDao.kt
│   └── SavedDatabase.kt
│   └── SavedEntity.kt
├── util
│   └── Constants.kt
│   └── FragmentDrawable.kt
```
