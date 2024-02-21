window.addEventListener("DOMContentLoaded", function() {
    const startDateEl = document.getElementById("startDate");
    const endDateEl = document.getElementById("endDate");
    const subscriptionMonths = document.getElementById("subscriptionMonths");

    startDateEl.addEventListener("change", updateEndDate);
    subscriptionMonths.addEventListener("blur", updateEndDate);

    function updateEndDate() {
        if(!subscriptionMonths.value) return;

        const month = parseInt(subscriptionMonths.value);
        if (month < 1) return;

        const date = new Date(startDateEl.value);

        date.setMonth(date.getMonth() + month);
        const endDate = `${date.getFullYear()}-${("" + (date.getMonth() + 1))
        .padStart(2, '0')}-${("" + date.getDate()).padStart(2, '0')}`;

        endDateEl.value = endDate;

        console.log(date)
    }
});

function callbackFileUpload(files) {
    if (files == null || files.length == 0) return;

    const file = files[0];

    const img = new Image();
    img.src = file.fileUrl;

    const thumbEl = document.getElementById("thumbnail_img");
    thumbEl.innerHTML = "";
    thumbEl.appendChild(img);

    const aLink = document.createElement("a");
    aLink.innerHTML = "[삭제]"
    aLink.href=`/file/delete/${file.seq}`;
    aLink.target="ifrmProcess";
    aLink.addEventListener("click", function(e) {
        if (!confirm("정말 삭제하시겠습니까?")) {
            e.preventDefault();
        }
    });

    thumbEl.appendChild(aLink);

}

function callbackFileDelete(seq) {
    const thumbEl = document.getElementById("thumbnail_img");
    thumbEl.innerHTML = "";
}

function callbackFileUpload(files) {
    if (files == null || files.length == 0) return;

    const file = files[0];

    const img = new Image();
    img.src = file.fileUrl;

    const thumbEl = document.getElementById("thumbnail_img");
    thumbEl.innerHTML = "";
    thumbEl.appendChild(img);

    const aLink = document.createElement("a");
    aLink.innerHTML = "[삭제]"
    aLink.href=`/file/delete/${file.seq}`;
    aLink.target="ifrmProcess";
    aLink.addEventListener("click", function(e) {
        if (!confirm("정말 삭제하시겠습니까?")) {
            e.preventDefault();
        }
    });

    thumbEl.appendChild(aLink);

}

function callbackFileDelete(seq) {
    const thumbEl = document.getElementById("thumbnail_img");
    thumbEl.innerHTML = "";
}