const app = new Vue({
  el: '#app',
  data: {
    tasks: [],
    newTask: ''
  },
  methods: {
    addTask: function () {
      axios
        .post('http://127.0.0.1:8080/api/v1/task/create/', { name: this.newTask })
        .then(response => {
          if (response.status === 200) {
            this.tasks.push(response.data);
            this.newTask = '';
          }
        });
    },
    completeTask: function (id) {
      axios
        .patch('http://127.0.0.1:8080/api/v1/task/complete/' + id)
        .then(response => {
          if (response.status === 200) {
            this.tasks.find(task => task.id === id).complete = response.data;
          }
        });
    },
    deleteTask: function (id) {
      axios
        .delete('http://127.0.0.1:8080/api/v1/task/' + id)
        .then(response => {
          if (response.status === 200) {
            this.tasks = this.tasks.filter(task => task.id !== id);
          }
        });
    }
  },
  mounted() {
    axios
      .get('http://127.0.0.1:8080/api/v1/task/list')
      .then(response => {
        if (response.status === 200) {
          this.tasks = response.data;
        }
      });
  }
});
