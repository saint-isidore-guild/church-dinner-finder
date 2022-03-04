import { mount } from '@vue/test-utils'
import EventCard from '~/components/EventCard'

describe('EventCard', () => {
  test('is a Vue instance', () => {
    const propsData = {
      event: {
        title: 'My Fish Fry',
        parish: {
          website: 'https://github.com',
        },
      },
    }

    const wrapper = mount(EventCard, { propsData })
    expect(wrapper.vm).toBeTruthy()
  })
})
