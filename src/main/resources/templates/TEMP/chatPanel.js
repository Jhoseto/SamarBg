document.addEventListener('DOMContentLoaded', function() {
  // Избиране на всички елементи от списъка с потребители
  const userListItems = document.querySelectorAll('.list .clearfix');

  // Прикачване на слушател за събитие click на всеки елемент от списъка
  userListItems.forEach(function(item) {
    // Получаване на идентификационния номер на потребителя от атрибута th:data-user-id
    const userId = item.getAttribute('th:data-user-id');

    // Добавяне на слушател за клик, който извиква функцията openChat със съответния userId
    item.addEventListener('click', function(event) {
      openChat(userId);
    });
  });
});

// Функция за отваряне на чат с определен потребител
function openChat(userId) {
  // Променете заглавието на чата
  const chatHeader = document.querySelector('.chat .chat-header .chat-with');
  chatHeader.textContent = 'Chat with User ' + userId;

  // Покажете чат прозореца (зависи от вашата имплементация)
  const chatPanel = document.querySelector('.chat');
  chatPanel.style.display = 'block';
}



// Обработчик за изпращане на съобщение
function sendMessage() {
  var messageContent = document.getElementById('message-to-send').value;
  var chatMessage = {
    content: messageContent,
    sender: 'currentUserId', // Идентификация на текущия потребител
    timestamp: new Date().toISOString()
  };

  // Изпратете съобщението чрез WebSocket към сървъра
  // Тук трябва да добавите логика за изпращане на съобщението
  console.log('Sending message:', chatMessage);

  // Изчистване на текстовото поле след изпращане на съобщението
  document.getElementById('message-to-send').value = '';
}
