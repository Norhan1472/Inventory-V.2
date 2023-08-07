
document.addEventListener('DOMContentLoaded', function() {
if (document.querySelector('.open-btn')) {
  document.querySelector('.open-btn').addEventListener('click', function() {
    document.querySelector('.popup').style.display = 'block';
  });

  document.querySelector('.close-btn').addEventListener('click', function() {
    document.querySelector('.popup').style.display = 'none';
  });
}});
/*

document.addEventListener('DOMContentLoaded', function() {
  document.querySelector('.open-btn').addEventListener('click', function() {
    document.querySelector('.popup').style.display = 'block';
  });

  document.querySelector('.close-btn').addEventListener('click', function() {
    document.querySelector('.popup').style.display = 'none';
  });
});*/
