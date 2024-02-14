// boardAdd.js

/* 새로 만든 파일 첨부 버튼 S */
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('uploadButton').addEventListener('click', function() {
        document.getElementById('uploadFile').click();
    });
});
/* 새로 만든 파일 첨부 버튼 E */

/* 파일 첨부 및 검증 S */
document.addEventListener('DOMContentLoaded', function() {
    // 파일 선택 input 요소
    var fileInput = document.getElementById('uploadFile');

    // 파일 선택 버튼
    var uploadButton = document.getElementById('uploadButton');

    // 파일 정보를 표시하는 요소
    var fileInfo = document.getElementById('fileInfo');

    // 파일이 선택되면 파일 정보를 표시하는 함수
    fileInput.addEventListener('change', function() {
        // 파일이 선택되었는지 확인
        if (fileInput.files.length > 0) {
            // 선택된 파일의 정보 가져오기
            var selectedFile = fileInput.files[0];
            var fileName = selectedFile.name;
            var fileSize = selectedFile.size; // 파일 크기 (바이트 단위)

            // 파일 크기를 MB 단위로 변환
            var fileSizeInMB = fileSize / (1024 * 1024);

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