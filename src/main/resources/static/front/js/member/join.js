window.addEventListener("DOMContentLoaded", function() {
  const idDupCheckBtn = document.querySelector('#userId_dup_check');
  const nickDupCheckBtn = document.querySelector('#nickname_dup_check');

  if (idDupCheckBtn) {
    idDupCheckBtn.addEventListener("click", function() {
      const idOkEl = document.querySelector(".id_check_ok");
      idOkEl.innerHTML = "";

      const userId = frmJoin.userId.value.trim();
      console.log(userId);

      // 아이디 유효성을 검사하는 정규식 추가
      const idRegex = /^[a-zA-Z0-9_]{6,20}$/;

      // 아이디 길이 제한 추가
      if (userId.length >= 6 && userId.length <= 20 && idRegex.test(userId)) {
        const { ajaxLoad } = commonLib;

        ajaxLoad("GET", `/api/member/userId_dup_check?userId=${userId}`, null, "json")
            .then(data => {
              if (data.success) {
                alert("중복 사용중인 아이디입니다.");
                frmJoin.userId.focus();
              } else {
                alert("사용 가능한 아이디입니다.");
                idOkEl.innerHTML = "<span class='confirmed'>사용 가능한 아이디입니다.</span>";
              }
            });
      } else {
        alert(" 6자 이상으로 입력하세요. 공백없이 영문/숫자 6자만 사용 가능합니다.");
      }
    });
  }
});

function MaxMinDate() {
  const selectedDate = new Date(document.getElementsByName('bDay')[0].value);
  const currentDate = new Date();
  const minDate = new Date('1824-01-01');
  const maxDate = currentDate;

  if (selectedDate > maxDate) {
    alert('오늘 날짜 이전으로만 선택할 수 있습니다.');
    document.getElementsByName('bDay')[0].value = '';
  } else if (selectedDate < minDate) {
    alert('해당 날짜 이후로만 선택할 수 있습니다.');
    document.getElementsByName('bDay')[0].value = '';
  }
}
