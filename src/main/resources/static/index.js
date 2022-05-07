    /*뒤로가기 금지*/
    history.pushState(null, null, 'http://localhost:8080/index.html'); /* 뒤로가기할경우 로그인페이지*/
    window.onpopstate = function (event) {
        history.go(1)
    };

    /* index.html js 페이지 */
    /* 시작 버튼 */
    function nick_Login() {
        window.location.href = 'room_list.html'
    };

    /* room_list js 페이지 */
    /* 방 만들기 버튼 */
    function create_btn() {
        window.location.href = 'room_create.html'
    };
    /* 나가기 버튼 */
    function back_btn() {
        window.location.href = 'index.html'
    };

    /* room_create js 페이지 */
    /* 이전 버튼 */
    function create_back_btn() {
        window.location.href = 'room_list.html'
    };

    /* room_in js 페이지 */
    /* 게임 나가기 버튼 */
    function game_back_btn() {
        window.location.href = 'room_list.html' /* 방 생성 url 지정 */
    };
