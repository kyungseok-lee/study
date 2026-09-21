package main

import (
	"github.com/kyungseok-lee/study/go-tuckersGo-goWeb/web03-test/src/myapp"
	"github.com/kyungseok-lee/study/go-tuckersGo-goWeb/web03-test/src/utils"
	"net/http"
)

func main() {
	utils.Hello()
	utils.Say()
	//utils.foo()
	http.ListenAndServe(":3000", myapp.NewHttpHandler())
}
