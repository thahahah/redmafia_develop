    let arr = [];
    let i = 0;

    /*뒤로가기 금지*/
    history.pushState(null, null, 'index.html'); /* 뒤로가기할경우 로그인페이지*/
    window.onpopstate = function (event) {
        history.go(1)
    };

    fetch('test')
        .then((response) => response.json())
        .then((data) => console.log(data));

    /* index.html js 페이지 */
    /* 시작 버튼 */
    function nick_Login() {
        fetch('Login', {
            method : 'POST'
        })
            .then((response) => response.json())
            .then((data) => {
                window.location.href = 'room_list.html'
                console.log(data)
            });

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
    /* 방 생성 표시 */

    /* room_create js 페이지 */
    /* 이전 버튼 */
    function create_back_btn() {
        window.location.href = 'room_list.html'
    };

    function create_room_btn() {
        fetch('create_room', {
            method : 'POST'
        }) /* 방 생성 fetch */
            .then((response => response.json()))
            .then((data) => {
                /* 방 생성 표시 */
            })
        window.location.href = 'room_list.html'
    }

    /* room_in js 페이지 */
    /* 게임 나가기 버튼 */
    function game_back_btn() {
        window.location.href = 'room_list.html'
    };

    function room_join_btn() {
        window.location.href = 'room_in.html'
    };
