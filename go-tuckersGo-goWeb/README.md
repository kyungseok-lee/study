# Tucker의 GoLang 프로그래밍 study
- 원문: Tucker의 GoLang 프로그래밍
  - github: https://github.com/tuckersGo/goWeb
  - youtube: https://www.youtube.com/channel/UCZp_ftx6UB_32VfVmlS3o_A

## 로컬 실행과 검증

이 예제는 `kyungseok-lee/study` 저장소의 하위 프로젝트입니다. `web01/src`, `web02-json/src`, `web03-test/src`는 각 디렉터리의 실제 GitHub 경로를 모듈명으로 사용하는 독립적인 Go 모듈입니다.

```bash
git clone https://github.com/kyungseok-lee/study.git
cd study/go-tuckersGo-goWeb
for lesson in web01 web02-json web03-test; do
  (cd "$lesson/src" && go build ./... && go test ./...) || exit 1
done
```

## Test
- [GoConvey](https://github.com/smartystreets/goconvey)
```
go get github.com/smartystreets/goconvey
$GOPATH/bin/goconvey
```

- [testify](https://github.com/stretchr/testify)
```
go get github.com/stretchr/testify/assert
```
```
github.com/stretchr/testify/assert
github.com/stretchr/testify/require
github.com/stretchr/testify/mock
github.com/stretchr/testify/suite
github.com/stretchr/testify/http (deprecated)
```