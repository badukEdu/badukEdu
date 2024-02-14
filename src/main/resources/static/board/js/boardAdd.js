// boardAdd.js


/* 새로 만든 파일 첨부 버튼 S */
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('uploadButton').addEventListener('click', function() {
        document.getElementById('uploadFile').click();
    });
});
/* 새로 만든 파일 첨부 버튼 E */


/* 파일 드롭 영역 이벤트 처리 S */
document.addEventListener('DOMContentLoaded', function() {
    // 파일 드롭 영역 선택
    const dropArea = document.getElementById('dropArea');

    // 파일 선택 input 요소
    const fileInput = document.getElementById('uploadFile');

    // 파일 정보를 표시하는 요소
    const fileInfo = document.getElementById('fileInfo');

    // 파일 드롭 이벤트 처리
    dropArea.addEventListener('dragover', function(e) {
        e.preventDefault();
        dropArea.classList.add('dragover');
    });

    dropArea.addEventListener('dragleave', function() {
        dropArea.classList.remove('dragover');
    });

    dropArea.addEventListener('drop', function(e) {
        e.preventDefault();
        dropArea.classList.remove('dragover');

        // 드롭된 파일 정보 가져오기
        const files = e.dataTransfer.files;

        // 파일 정보 표시
        if (files.length > 0) {
            const fileName = files[0].name;
            fileInfo.textContent = '선택된 파일: ' + fileName;

//            const formData = new FormData();
//            for (const file of files) {
//                formData.append("file", file);
//            }

        } else {
            fileInfo.textContent = '선택된 파일 없음';
        }
    });
});
/* 파일 드롭 영역 이벤트 처리 E */


/* 파일 첨부 및 검증 S */
document.addEventListener('DOMContentLoaded', function() {
    // 파일 선택 input 요소
    const fileInput = document.getElementById('uploadFile');

    /*
    파일 선택 버튼 필요 시 주석 풀고 사용
    const uploadButton = document.getElementById('uploadButton');
    */

    // 파일 정보를 표시하는 요소
    const fileInfo = document.getElementById('fileInfo');

    // 파일이 선택되면 파일 정보를 표시하는 함수
    fileInput.addEventListener('change', function() {
        // 파일이 선택되었는지 확인
        if (fileInput.files.length > 0) {
            // 선택된 파일의 정보 가져오기
            const selectedFile = fileInput.files[0];
            const fileName = selectedFile.name;
            const fileSize = selectedFile.size; // 파일 크기 (바이트 단위)

            // 파일 크기를 MB 단위로 변환
            const fileSizeInMB = fileSize / (1024 * 1024);

            // 파일 이름 표시
            fileInfo.textContent = '첨부된 파일: ' + fileName;

            // 파일 크기가 30MB를 초과하는지 확인
            if (fileSizeInMB > 30) {
                // 경고 메시지 표시
                alert('첨부할 수 있는 파일의 크기를 초과하였습니다.');
                // 파일 선택 취소
                fileInput.value = "";
                // 파일 정보 초기화
                fileInfo.textContent = '선택된 파일 없음';
            }
        } else {
            // 파일이 선택되지 않은 경우 버튼 초기값으로 설정
            fileInfo.textContent = '선택된 파일 없음';
        }
    });

   // 비우기 버튼 클릭 시 첨부된 파일 지우기
       const clearButton = document.getElementById('clearButton');
       clearButton.addEventListener('click', function(event) {
           event.preventDefault(); // 버튼 클릭 시 기본 동작인 submit 방지
           fileInput.value = ""; // 파일 선택란 비우기
           fileInfo.textContent = '선택된 파일 없음'; // 파일 정보 초기화
        });
});
/* 파일 첨부 및 검증 E */


/* 라디오 버튼 2번 클릭 시 선택 취소 S */
 document.addEventListener("DOMContentLoaded", function() {
        const radioButtons = document.querySelectorAll('input[type="radio"]');

        radioButtons.forEach(function(radioButton) {
            radioButton.addEventListener("click", function() {
                if (this.previousValue === "checked") {
                    this.checked = false;
                    this.previousValue = false;
                } else {
                    this.previousValue = this.checked ? "checked" : "unchecked";
                }
            });
        });
    });
/* 라디오 버튼 2번 클릭 시 선택 취소 E */


/* 분류에 따라 보여지는 form 변경 S */
document.addEventListener('DOMContentLoaded', function() {
    // 공지 분류 선택 요소
    const noticeCategorySelect = document.getElementById('noticeCategory');
    // FAQ 분류 선택 요소
    const faqCategorySelect = document.getElementById('faqCategory');
    // 공지사항 관련 필드
    const noticeFields = document.querySelectorAll('.noticeRow');
    // FAQ 관련 필드
    const faqFields = document.querySelectorAll('.faqRow');

    // 초기 상태 설정
    toggleFields(noticeCategorySelect.value);

    // 공지 분류 선택 시 이벤트 리스너 추가
    noticeCategorySelect.addEventListener('change', function() {
        toggleFields(noticeCategorySelect.value);
    });

    // FAQ 분류 선택 시 이벤트 리스너 추가
    faqCategorySelect.addEventListener('change', function() {
        toggleFields(faqCategorySelect.value);
    });

    // 필드를 보이거나 숨기는 함수 정의
    function toggleFields(category) {
        if (category === 'notice') {
            noticeFields.forEach(function(field) {
                field.style.display = 'table-row'; // 공지사항 필드 표시
            });
            faqFields.forEach(function(field) {
                field.style.display = 'none'; // FAQ 필드 숨김
            });
            // 분류 선택 요소의 값을 변경
            faqCategorySelect.value = 'faq';
        } else if (category === 'faq') {
            noticeFields.forEach(function(field) {
                field.style.display = 'none'; // 공지사항 필드 숨김
            });
            faqFields.forEach(function(field) {
                field.style.display = 'table-row'; // FAQ 필드 표시
            });
            // 분류 선택 요소의 값을 변경
            noticeCategorySelect.value = 'notice';
        }
    }
});
/* 분류에 따라 보여지는 form 변경 E */


/* 게시 예정일 선택 시 달력 표출 S */

document.addEventListener('DOMContentLoaded', function() {
    // 게시 예정일 라디오 버튼
    const expectedPostingDateRadio = document.getElementById('expectedPostingDate');
    // 등록 즉시 게시 라디오 버튼
    const immediatelyRadio = document.getElementById('immediately');
    // 달력
    const calendar = document.getElementById('calendar');
    // 등록하기 버튼
    const addButton = document.getElementById('addNoticeFaq');

     // 내일 날짜 가져오기
        const TOMORROW = new Date();
        TOMORROW.setDate(TOMORROW.getDate() + 1);
        const TOMORROW_FORMATTED = TOMORROW.toISOString().split('T')[0];

    // 게시 예정일 선택 시 이벤트 리스너 추가
    expectedPostingDateRadio.addEventListener('change', function() {
        // 게시 예정일을 선택한 경우에만 달력을 보이도록 설정
        if (this.checked) {
            calendar.style.display = "inline-block";
            // 내일부터 선택 가능하도록 min 속성 설정
            calendar.setAttribute("min", TOMORROW_FORMATTED);
        } else {
            calendar.style.display = "none";
            // min 속성 제거
            calendar.removeAttribute("min");
        }
    });

    // 등록 즉시 게시 선택 시 달력 숨기기
        immediatelyRadio.addEventListener('change', function() {
            // 등록 즉시 게시가 선택된 경우 달력 숨기기
            calendar.style.display = "none";
        });

    // 게시 예정일 선택이 해제될 때 달력 숨기기
        immediatelyRadio.addEventListener('change', function() {
            calendar.style.display = "none";
        });
});
/* 게시 예정일 선택 시 달력 표출 E */