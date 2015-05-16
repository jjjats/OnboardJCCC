from django.contrib import admin
from students.models import Student, Progress


class ProgressInLine(admin.StackedInline):
    model = Progress
    extra = 1


# class StudentAdmin(admin.ModelAdmin):
#     fieldsets = [
#         (None,  {'fields': ['first_name', 'last_name']})
#         ]

#     inlines = [ProgressInLine]


class ProgressAdmin(admin.ModelAdmin):
    fields = ['student', 'percentage']
    list_display = ('student', 'percentage')

admin.site.register(Progress)
