(function() {
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
        //  функция за изпращане на съобщение до другия потребител

         this.sendToOtherUser(message);
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
})();
