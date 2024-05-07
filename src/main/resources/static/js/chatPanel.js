// WebSocket connection
function connect() {
  var socket = new SockJS('/ws');
  stompClient = Stomp.over(socket);

  stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);

    // Subscribe to the workers topic
    stompClient.subscribe('/topic/chat', function(message) {
      var workersData = JSON.parse(message.body);

    });
  }, function(error) {
    console.error('WebSocket connection error:', error);
  });
}

document.addEventListener('DOMContentLoaded', function() {
  // Избиране на всички елементи от списъка с потребители
  const userListItems = document.querySelectorAll('.list .clearfix');

 // connect()
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

// Function to send message to other user through WebSocket
function sendToOtherUser(message) {
  if (socket.readyState === WebSocket.OPEN) {
    socket.send(message);
  } else {
    console.error('WebSocket connection is not open.');
  }
}

// Chat functionality
var chat = {
  init: function() {
    this.cacheDOM();
    this.bindEvents();
  },
  cacheDOM: function() {
    this.$chatHistoryList = $('#chat-history-list');
    this.$textarea = $('#message-to-send');
    this.$button = $('#sendButton');
  },
  bindEvents: function() {
    this.$button.on('click', this.sendMessage.bind(this));
    this.$textarea.on('keyup', function(event) {
      if (event.keyCode === 13) {
        this.sendMessage();
      }
    }.bind(this));
  },
  sendMessage: function() {
    var message = this.$textarea.val().trim();
    if (message !== '') {
      this.renderMessage('You', message);
      sendToOtherUser(message);
      this.$textarea.val('');
    }
  },
  renderMessage: function(sender, message) {
    var messageHTML = '<li><div class="message-data">' + sender + ': ' + this.getCurrentTime() + '</div><div class="message">' + message + '</div></li>';
    this.$chatHistoryList.append(messageHTML);
    this.scrollToBottom();
  },
  scrollToBottom: function() {
    this.$chatHistoryList.scrollTop(this.$chatHistoryList[0].scrollHeight);
  },
  getCurrentTime: function() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, '$1$3');
  }
};

chat.init();
