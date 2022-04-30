window.onload = function () {
    /*뒤로가기 금지*/
    history.pushState(null, null, 'http://localhost:63342/demo/static/index.html?_ijt=nmf9c6ipgrl86v1vqsmc6lc49j&_ij_reload=RELOAD_ON_SAVE'); /* 뒤로가기할경우 로그인페이지*/
    window.onpopstate = function (event) {
        history.go(1)
    };

    /* 로그인 */
    document.getElementById("nick_submit").addEventListener('click',nick_Login)
        function nick_Login() {
            window.location.href = 'http://localhost:63342/demo/static/room_list.html?_ijt=figuq43d40j64hehrvp566uqjp&_ij_reload=RELOAD_ON_SAVE'; /* 방 목록 url 지정 */
        }

        /* 방 목록 나가기 */
    document.getElementById("create_btn").addEventListener('click', create_btn)
        function create_btn() {
            window.location.href = 'http://localhost:63342/demo/static/index.html?_ijt=nmf9c6ipgrl86v1vqsmc6lc49j&_ij_reload=RELOAD_ON_SAVE' /* 방 생성 url 지정 */
        }

}